package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
class Server extends ConnectionListenerAdapter {

    private final Property property;
    private final List<Connection> connections = new CopyOnWriteArrayList<>();
    private final ExecutorService requestExecutorService;
    private final ExecutorService sendExecutor;
    private ServerSocket serverSocket;
    private ScheduledExecutorService connectionMonitoringService;
    private ScheduledExecutorService requestMonitoringService;
    private ScheduledExecutorService expiredConnectionMonitoringService;

    Server(Property property) {
        this.property = property;
        requestExecutorService = Executors.newFixedThreadPool(property.getRequestMonitoringThreadCount());
        sendExecutor = Executors.newFixedThreadPool(property.getSendExecutorThreadCount());
    }

    void start() {
        openServerSocket();
        startMonitoringConnections();
        startMonitoringInputRequests();
        startExpiredConnectionsMonitoring();
        log.info("Сервер запущен.");
    }

    void stop() {
        connectionMonitoringService.shutdownNow().forEach(System.out::println);
        requestMonitoringService.shutdown();
        expiredConnectionMonitoringService.shutdown();
        closeConnections();
        closeServerSocket();
        log.info("Сервер выключен.");
    }

    @Override
    public void onClientMessage(ClientMessageDto clientMessageDto, Connection connection) {
        sendDtoToAllAuthorizedConnections(createMessage(clientMessageDto.getAuthor(), clientMessageDto.getMessage()));
    }

    @Override
    public void onLoginRequest(LoginRequestDto loginRequestDto, Connection connection) {
        checkToConfirmLogin(loginRequestDto.getLogin(), connection);
        if (connection.isAuthorized()) {
            sendDtoToAllAuthorizedConnections(createMessage(property.getServerLogin(), connection.getLogin() + " join to chat."));
        }
    }

    @Override
    public void onLoginReconnect(LoginReconnectDto loginReconnectDto, Connection connection) {
        checkToConfirmLogin(loginReconnectDto.getLogin(), connection);
    }

    private void checkToConfirmLogin(String newLogin, Connection connection) {
        if (!connection.isAuthorized()) {
            if (!getAuthorizedLogin().contains(newLogin)) {
                connection.setLogin(newLogin);
                sendDto(connection, new LoginResponseDto(newLogin, true));
                sendDtoToAllAuthorizedConnections(new UserOnlineDto(getAuthorizedLogin()));
            } else {
                sendDto(connection, new LoginResponseDto(newLogin, false));
            }
        }
    }

    @Override
    public void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection) {
        connection.disconnect();
    }

    @Override
    public void onCallBackPing(CallBackPingDto callBackPingDto, Connection connection) {
        sendDto(connection, new PingDto());
    }

    @Override
    public void onDisconnect(Connection connection) {
        connections.remove(connection);
        log.info("Connection: {} was disconnected.", connection);
        sendDtoToAllAuthorizedConnections(createMessage(property.getServerLogin(), "User: " + connection.getLogin() + " left from chat."));
        sendDtoToAllAuthorizedConnections(new UserOnlineDto(getAuthorizedLogin()));
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Exception in connection: {}", connection, e);
    }

    private void startMonitoringConnections() {
        connectionMonitoringService = Executors.newSingleThreadScheduledExecutor();
        connectionMonitoringService.scheduleAtFixedRate(() -> {
            try {
                Connection newSocketConnection = new Connection(serverSocket.accept(),
                        TimeUnit.MILLISECONDS.convert(property.getNonActivityConnectionLiveTime(), TimeUnit.SECONDS), this);
                connections.add(newSocketConnection);
            } catch (IOException e) {
                log.error("Error to accept connection.", e);
            }
        }, 0, 20, TimeUnit.MILLISECONDS);
    }

    private void startMonitoringInputRequests() {
        requestMonitoringService = Executors.newSingleThreadScheduledExecutor();
        requestMonitoringService.scheduleAtFixedRate(() -> connections.stream()
                .filter(Connection::isAvailable)
                .forEach(connection -> requestExecutorService
                        .execute(connection::callRequestAction)), 0, property.getRequestMonitoringPeriod(), TimeUnit.MILLISECONDS);
    }

    private void startExpiredConnectionsMonitoring() {
        expiredConnectionMonitoringService = Executors.newSingleThreadScheduledExecutor();
        expiredConnectionMonitoringService.scheduleAtFixedRate(() -> connections.stream()
                .filter(Connection::isExpired)
                .forEach(Connection::disconnect), 0, property.getConnectionCollectCheckTime(), TimeUnit.SECONDS);
    }

    private void sendDtoToAllAuthorizedConnections(Dto dto) {
        connections.stream()
                .filter(Connection::isAuthorized)
                .forEach(connection -> sendDto(connection, dto));
    }

    private void sendDto(Connection connection, Dto dto) {
        sendExecutor.execute(() -> connection.sendDto(dto));
    }

    private BroadCastMessageDto createMessage(String author, String message) {
        return Message.create(author, message);
    }

    private List<String> getAuthorizedLogin() {
        return connections.stream().filter(Connection::isAuthorized).map(Connection::getLogin).collect(Collectors.toList());
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(property.getServerPort());
        } catch (IOException e) {
            log.error("Не удалось открыть серверный сокет.", e);
        }
    }

    private void closeServerSocket() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("Не удалось закрыть серверный сокет.", e);
        }
    }

    private void closeConnections() {
        connections.forEach(connection -> sendDto(connection, new DisconnectRequestDto()));
        connections.forEach(Connection::disconnect);
        connections.clear();
    }
}
