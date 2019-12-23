package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.Dto;
import ru.cft.focusstart.dto.LoginResponseDto;
import ru.cft.focusstart.dto.MessageDto;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
class Server extends ConnectionListenerAdapter {

    private final Property property = new Property();
    private final List<Connection> connections = new CopyOnWriteArrayList<>();
    private Thread connectionCheckingThread;
    private Thread requestsCheckingThread;
    private ServerSocket serverSocket;

    Server() {
        openServerSocket();
        startMonitoringConnections();
        startMonitoringInputRequests();
        startConnectionsCollectorMonitoring();
        log.info("Server is running.");
    }

    @Override
    public void onMessage(String login, String message) {
        sendDtoToAllAuthorizedConnections(new MessageDto(login, message));
    }

    @Override
    public void onLoginRequest(String login, Connection connection) {
        if (!connection.isAuthorized()) {
            boolean loginAccepted = connections.stream()
                    .filter(Connection::isAuthorized)
                    .map(Connection::getLogin)
                    .noneMatch(someLogin -> someLogin.equals(login));
            if (loginAccepted) {
                connection.setLogin(login);
                sendDtoToAllAuthorizedConnections(new MessageDto(property.getServerLogin(), login + " join to chat."));
            }
            sendDto(connection, new LoginResponseDto(login, loginAccepted));
        }
    }

    @Override
    public void onDisconnectRequest(Connection connection) {
        connection.disconnect();
    }

    @Override
    public void onDisconnect(Connection connection) {
        connections.remove(connection);
        log.info("Connection: {} was disconnected.", connection);
        sendDtoToAllAuthorizedConnections(new MessageDto(property.getServerLogin(), "User: " + connection.getLogin() + " left from chat."));
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Exception in connection: {}", connection, e);
    }

    private void startMonitoringConnections() {
        connectionCheckingThread = new Thread(() -> {
            while (!connectionCheckingThread.isInterrupted()) {
                try {
                    Connection newSocketConnection = new Connection(serverSocket.accept(),
                            TimeUnit.MILLISECONDS.convert(property.getNonActivityConnectionLiveTime(), TimeUnit.MINUTES), this);
                    connections.add(newSocketConnection);
                } catch (IOException e) {
                    log.error("Error to accept connection.", e);
                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    log.error("Monitoring Thread Error.");
                }
            }
        });
        connectionCheckingThread.start();
    }

    private void startMonitoringInputRequests() {
        ExecutorService executorService = Executors.newFixedThreadPool(property.getMessageMonitoringThreadCount());
        requestsCheckingThread = new Thread(() -> {
            while (!requestsCheckingThread.isInterrupted()) {
                connections.stream()
                        .filter(Connection::isAvailable)
                        .forEach(connection -> executorService.execute(connection::callRequestAction));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        requestsCheckingThread.start();
    }

    private void startConnectionsCollectorMonitoring() {
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
        connection.sendDto(dto);
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(property.getServerPort());
        } catch (IOException e) {
            log.error("server error", e);
        }
    }
}
