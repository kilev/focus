package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.Dto;
import ru.cft.focusstart.dto.LoginRequestDto;
import ru.cft.focusstart.dto.MessageDto;

import java.io.IOException;

@Slf4j
public class ServerCommunicationService extends ConnectionListenerAdapter {

    private final chatMonitor chatMonitor;
    private Connection connection;
    private Thread serverListenerThread;

    ServerCommunicationService(chatMonitor chatMonitor) {
        this.chatMonitor = chatMonitor;
    }

    public void newConnection(String host, Integer port, String login) {
        try {
            connection = new Connection(host, port, null, this);
            sendDto(new LoginRequestDto(login));
        } catch (IOException e) {
            log.error("socket connection error", e);
        }
    }

    public void sendMessage(String message) {
        if (connection != null && connection.isAuthorized()) {
            sendDto(new MessageDto(connection.getLogin(), message));
        }
    }

    @Override
    public void onMessage(String login, String message) {
        chatMonitor.addMessage(login, message);
    }

    @Override
    public void onLoginResponse(String login, boolean accepted) {
        if (accepted) {
            connection.setLogin(login);
            startListenServer();
        }
    }

    @Override
    public void onDisconnect(Connection connection) {
        serverListenerThread.interrupt();
        connection.disconnect();
        log.info("Disconnect from server.");
    }

    @Override
    public void onException(Connection connection, Exception e) {

    }

    private void startListenServer() {
        serverListenerThread = new Thread(() -> {
            while (!serverListenerThread.isInterrupted()) {
                if (connection.isAvailable()) {
                    connection.getDtoAction();
                }
            }
        });
        serverListenerThread.start();
    }

    private void sendDto(Dto dto) {
        connection.sendDto(dto);
    }
}
