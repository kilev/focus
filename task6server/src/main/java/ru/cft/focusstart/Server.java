package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@RequiredArgsConstructor
class Server extends ConnectionListenerAdapter {

    private final Property property;
    private final List<Connection> connections = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket;
    private final ExecutorService sendExecutor = Executors.newFixedThreadPool(10);

    void start() {
        openServerSocket();
        startMonitoringConnections();
        startMonitoringInputRequests();
        startExpiredConnectionsMonitoring();
        log.info("Server is running.");
    }

    @Override
    public void onMessage(MessageDto messageDto, Connection connection) {
        sendDtoToAllAuthorizedConnections(createMessage(messageDto.getAuthor(), messageDto.getMessage()));
    }

    @Override
    public void onLoginRequest(LoginRequestDto loginRequestDto, Connection connection) {
        if (!connection.isAuthorized()) {
            String newLogin = loginRequestDto.getLogin();
            boolean loginAccepted = connections.stream()
                    .filter(Connection::isAuthorized)
                    .map(Connection::getLogin)
                    .noneMatch(someLogin -> someLogin.equals(newLogin));
            if (loginAccepted) {
                connection.setLogin(newLogin);
                sendDtoToAllAuthorizedConnections(createMessage(property.getServerLogin(), newLogin + " join to chat."));
            }
            sendDto(connection, new LoginResponseDto(newLogin, loginAccepted));
        }
    }

    @Override
    public void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection) {
        connection.disconnect();
    }

    @Override
    public void onDisconnect(Connection connection) {
        connections.remove(connection);
        log.info("Connection: {} was disconnected.", connection);
        sendDtoToAllAuthorizedConnections(createMessage(property.getServerLogin(), "User: " + connection.getLogin() + " left from chat."));
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Exception in connection: {}", connection, e);
    }

    private void startMonitoringConnections() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Connection newSocketConnection = new Connection(serverSocket.accept(),
                        TimeUnit.MILLISECONDS.convert(property.getNonActivityConnectionLiveTime(), TimeUnit.MINUTES), this);
                connections.add(newSocketConnection);
            } catch (IOException e) {
                log.error("Error to accept connection.", e);
            }
        });
    }

    private void startMonitoringInputRequests() {
        ExecutorService requestExecutorService = Executors.newFixedThreadPool(property.getRequestMonitoringThreadCount());
        ScheduledExecutorService requestMonitoringService = Executors.newSingleThreadScheduledExecutor();
        requestMonitoringService.scheduleAtFixedRate(() -> connections.stream()
                .filter(Connection::isAvailable)
                .forEach(connection -> requestExecutorService
                        .execute(connection::callRequestAction)), 0, property.getRequestMonitoringPeriod(), TimeUnit.MILLISECONDS);
    }

    private void startExpiredConnectionsMonitoring() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> connections.stream()
                .filter(Connection::isExpired)
                .forEach(Connection::disconnect), 0, property.getConnectionCollectCheckTime(), TimeUnit.MINUTES);
    }

    private void sendDtoToAllAuthorizedConnections(Dto dto) {
        connections.stream()
                .filter(Connection::isAuthorized)
                .forEach(connection -> sendDto(connection, dto));
    }

    private void sendDto(Connection connection, Dto dto) {
        sendExecutor.execute(() -> connection.sendDto(dto));
    }

    private MessageDto createMessage(String author, String message) {
        return Message.create(author, message);
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(property.getServerPort());
        } catch (IOException e) {
            log.error("server error", e);
        }
    }
}
