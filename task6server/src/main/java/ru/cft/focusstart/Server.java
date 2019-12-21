package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.Dto;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
class Server {

    private final Property property = new Property();
    private final List<SocketConnection> connections = Collections.synchronizedList(new ArrayList<>());
    private Thread connectionCheckingThread;
    private Thread messageCheckingThread;
    private ServerSocket serverSocket;

    Server() {
        openServerSocket();
        startMonitoringConnections();
        startMonitoringInputDto();
        startConnectionsCollectorMonitoring();
        log.info("Server is running.");
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(property.getServerPort());
        } catch (IOException e) {
            log.error("server error", e);
        }
    }

    private void startMonitoringConnections() {
        connectionCheckingThread = new Thread(() -> {
            while (!connectionCheckingThread.isInterrupted()) {
                try {
                    SocketConnection newSocketConnection = new SocketConnection(serverSocket.accept(),
                            TimeUnit.MILLISECONDS.convert(property.getNonActivityConnectionLiveTime(), TimeUnit.MINUTES),
                            new ServerConnectionListener());
                    synchronized (this) {
                        connections.add(newSocketConnection);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connectionCheckingThread.start();
    }

    private void startMonitoringInputDto() {
        ExecutorService executorService = Executors.newFixedThreadPool(property.getMessageMonitoringThreadCount());
        messageCheckingThread = new Thread(() -> {
            while (!messageCheckingThread.isInterrupted()) {
                connections.stream()
                        .filter(SocketConnection::isAvailable)
                        .forEach(author -> {
                            Dto inputDto = author.getDtoAction();
                            switch (inputDto.getStatus()) {
                                case CONFIRM_LOGIN_REQUEST:
                                    executorService.execute(() -> checkToConfirmLogin(author, inputDto.getLogin()));
                                    break;
                                case DISCONNECT_REQUEST:
                                    executorService.execute(author::disconnect);
                                    break;
                                case MESSAGE:
                                    executorService.execute(() -> sendMessageToAllAuthorizedConnections(inputDto.getLogin(), inputDto.getMessage()));
                                    break;
                            }
                        });
            }
        });
        messageCheckingThread.start();
    }

    private void startConnectionsCollectorMonitoring() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> connections.stream()
                .filter(SocketConnection::isExpired)
                .forEach(SocketConnection::disconnect), 0, property.getConnectionCollectCheckTime(), TimeUnit.MINUTES);
    }

    private void sendMessageToAllAuthorizedConnections(String author, String message) {
        Dto messageDto = new Dto(author, Status.MESSAGE, message);
        connections.stream()
                .filter(SocketConnection::isAuthorized)
                .forEach(socketConnection -> sendDto(socketConnection, messageDto));
    }

    private synchronized void checkToConfirmLogin(SocketConnection socketConnection, String newLogin) {
        if (!socketConnection.isAuthorized()) {
            boolean loginAccepted = connections.stream()
                    .filter(SocketConnection::isAuthorized)
                    .map(SocketConnection::getLogin)
                    .noneMatch(login -> login.equals(newLogin));
            if (loginAccepted) {
                socketConnection.setLogin(newLogin);
                sendDto(socketConnection, property.getServerLogin(), Status.OK, null);
                sendMessageToAllAuthorizedConnections(property.getServerLogin(), "User: " + newLogin + " join to chat.");
            } else {
                sendDto(socketConnection, property.getServerLogin(), Status.BAD_LOGIN, null);
            }
        }
    }

    private synchronized void removeConnection(SocketConnection socketConnection) {
        connections.remove(socketConnection);
        log.info("SocketConnection: {} was disconnected.", socketConnection);
        sendMessageToAllAuthorizedConnections(property.getServerLogin(), "User: " + socketConnection.getLogin() + " left from chat.");
    }

    private void sendDto(SocketConnection socketConnection, String login, Status status, String message) {
        socketConnection.sendDto(new Dto(login, status, message));
    }

    private void sendDto(SocketConnection socketConnection, Dto dto) {
        socketConnection.sendDto(dto);
    }

    class ServerConnectionListener implements ConnectionListener {


    }
}
