package ru.cft.focusstart.serverCommunicator;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListenerAdapter;
import ru.cft.focusstart.dto.*;
import ru.cft.focusstart.view.MainView;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerCommunicator extends ConnectionListenerAdapter {

    private final MainView view;
    private final MessageReceiver messageReceiver;
    private ScheduledExecutorService serverListener;
    private ScheduledExecutorService pingExecutor;
    private Connection connection;
    private boolean serverIsDisconnected;

    public ServerCommunicator(MainView view) {
        this.view = view;
        messageReceiver = new MessageReceiver(1000, view);
    }

    public void newConnection(String host, Integer port, String login) {
        try {
            if (connection != null) {
                sendDto(new DisconnectRequestDto());
                disconnect();
            }
            connection = new Connection(host, port, null, this);
            Thread.sleep(400);
            messageReceiver.start();
            startListenServer();
            startPingServer();
            sendDto(new LoginRequestDto(login));
        } catch (IOException | InterruptedException e) {
            log.error("socket connection error", e);
            view.showInfoPane("Не удалось подключиться к серверу");
        }
    }


    public void sendMessage(String message) {
        if (serverIsDisconnected) {
            disconnect();
            reconnect();
        }
        if (connection != null && connection.isAuthorized()) {
            sendDto(new ClientMessageDto(connection.getLogin(), message));
        }
    }

    public void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }

    @Override
    public void onBroadcastMessage(BroadCastMessageDto messageDto, Connection connection) {
        messageReceiver.receive(messageDto);
    }

    @Override
    public void onLoginResponse(LoginResponseDto loginResponseDto, Connection connection) {
        if (loginResponseDto.isAccepted()) {
            connection.setLogin(loginResponseDto.getLogin());
        } else {
            view.showInfoPane("Пользователь с таким логином уже существует.");
        }
    }

    @Override
    public void onUserOnline(UserOnlineDto userOnlineDto, Connection connection) {
        view.setUserOnlineArea(userOnlineDto.getUserOnline());
    }

    @Override
    public void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection) {
        serverIsDisconnected = true;
    }

    @Override
    public void onDisconnect(Connection connection) {
        serverListener.shutdown();
        pingExecutor.shutdown();
        messageReceiver.stop();
        log.info("Соединение с сервером разорвано.");
    }

    @Override
    public void onException(Connection connection, Exception e) {
        log.error("Ошибка общения с сервером", e);
    }

    private void reconnect() {
        try {
            String login = connection.getLogin();
            connection = connection.reconect();
            Thread.sleep(400);
            messageReceiver.start();
            startListenServer();
            startPingServer();
            sendDto(new LoginReconnectDto(login));
            Thread.sleep(1000);
            serverIsDisconnected = false;
        } catch (IOException | InterruptedException e) {
            view.showInfoPane("Не удалось подключиться к серверу.");
        }
    }

    private void startListenServer() {
        serverListener = Executors.newSingleThreadScheduledExecutor();
        serverListener.scheduleAtFixedRate(() -> {
            if (connection.isAvailable()) {
                connection.callRequestAction();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    private void startPingServer() {
        pingExecutor = Executors.newSingleThreadScheduledExecutor();
        pingExecutor.scheduleAtFixedRate(this::pingServer, 0, 5, TimeUnit.SECONDS);
    }

    private void pingServer() {
        sendDto(new PingDto());
    }

    private void sendDto(Dto dto) {
        connection.sendDto(dto);
    }
}
