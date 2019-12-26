package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.DisconnectRequestDto;
import ru.cft.focusstart.dto.Dto;
import ru.cft.focusstart.dto.LoginRequestDto;
import ru.cft.focusstart.dto.MessageDto;
import ru.cft.focusstart.view.MainView;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ServerCommunicator extends ConnectionListenerAdapter {

    private final MainView view;
    private Connection connection;
    private Thread serverListenerThread;

    public void newConnection(String host, Integer port, String login) {
        try {
            if (connection != null) {
                sendDto(new DisconnectRequestDto());
                connection.disconnect();
            }
            connection = new Connection(host, port, null, this);
            Thread.sleep(500);
            startListenServer();
            sendDto(new LoginRequestDto(login));
        } catch (IOException | InterruptedException e) {
            log.error("socket connection error", e);
            view.showInfoPane("CONNECT ERROR!");
        }
    }

    public void sendMessage(String message) {
        if (connection != null && connection.isAuthorized()) {
            sendDto(new MessageDto(connection.getLogin(), message));
        }
    }

    @Override
    public void onMessage(String login, String message) {
        view.addMessage(login, message);
    }

    @Override
    public void onLoginResponse(String login, boolean accepted) {
        if (accepted) {
            connection.setLogin(login);
        } else {
            view.showInfoPane("BAD LOGIN!");
        }
    }

    @Override
    public void onUserOnline(List<String> userOnline) {

    }


    @Override
    public void onDisconnect(Connection connection) {
        serverListenerThread.interrupt();
        log.info("Disconnect from server.");
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Communication service error", e);
    }

    private void startListenServer() {
        serverListenerThread = new Thread(() -> {
            while (!serverListenerThread.isInterrupted()) {
                if (connection.isAvailable()) {
                    connection.callRequestAction();
                }
            }
        });
        serverListenerThread.start();
    }

    private void sendDto(Dto dto) {
        connection.sendDto(dto);
    }
}
