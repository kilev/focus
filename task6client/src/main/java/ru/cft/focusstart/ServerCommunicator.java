package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.*;
import ru.cft.focusstart.view.MainView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class ServerCommunicator extends ConnectionListenerAdapter {

    private final MainView view;
    private final MessageReceiver messageReceiver = new MessageReceiver(view);
    private ScheduledExecutorService serverListener;
    private Connection connection;

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
            sendDto(createMessage(connection.getLogin(), message));
        }
    }

    @Override
    public void onMessage(MessageDto messageDto, Connection connection) {
        messageReceiver.receive(messageDto);
    }

    @Override
    public void onLoginResponse(LoginResponseDto loginResponseDto, Connection connection) {
        if (loginResponseDto.isAccepted()) {
            connection.setLogin(loginResponseDto.getLogin());
        } else {
            view.showInfoPane("BAD LOGIN!");
        }
    }

    @Override
    public void onUserOnline(UserOnlineDto userOnlineDto, Connection connection) {

    }

    @Override
    public void onDisconnect(Connection connection) {
        serverListener.shutdown();
        log.info("Disconnect from server.");
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Communication service error", e);
    }

    private void startListenServer() {
        serverListener = Executors.newSingleThreadScheduledExecutor();
        serverListener.scheduleAtFixedRate(() -> {
            if (connection.isAvailable()) {
                connection.callRequestAction();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    private MessageDto createMessage(String author, String message) {
        return new MessageDto(author, message, null, null);
    }

    private void sendDto(Dto dto) {
        connection.sendDto(dto);
    }
}
