package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.MessageDto;

import java.io.IOException;

@Slf4j
public class ServerCommunicationService {

    private final chatMonitor chatMonitor;
    private Connection connection;

    private Thread socketCommunicationThread;

    ServerCommunicationService(chatMonitor chatMonitor) {
        this.chatMonitor = chatMonitor;
    }

    public void sendMessage(String message) {
        connection.sendDto(new MessageDto(connection.getLogin(), Status.MESSAGE, message));
    }

    public void connectToServer(String host, Integer port, String login) {
        if (connection != null) {
            connection.disconnect();
            socketCommunicationThread.interrupt();
        }
        try {
            connection = new Connection(host, port, null, new ConnectionListener() {
                @Override
                public void onDtoSended(Connection socketConnection, MessageDto dto) {
                    log.info("Dto: {} was sended to server.", dto);
                }

                @Override
                public void onDtoReaded(Connection socketConnection, MessageDto dto) {
                    log.info("Dto: {} was readed from server", dto);
                }

                @Override
                public void onDisconnect(Connection connection) {
                    log.info("ti otvalilsya(");
                }

                @Override
                public void onException(Connection connection, Exception e) {
                    log.error("Error in SocketConnection", e);
                }
            });
        } catch (IOException e) {
            log.error("socket connection error", e);
        }
        tryLoginAccept(login);
        startListenServer();
    }

    private void tryLoginAccept(String login) {
        connection.sendDto(new MessageDto(login, Status.CONFIRM_LOGIN_REQUEST, null));
        while (!connection.isAuthorized()) {
            if (connection.isAvailable()) {
                MessageDto inputDto = connection.getDtoAction();
                if (inputDto.getStatus() == Status.OK) {
                    connection.setLogin(login);
                } else if (inputDto.getStatus() == Status.BAD_LOGIN) {
                    //show user that login is bad;
                }
            }
        }
    }

    private void startListenServer() {
        socketCommunicationThread = new Thread(() -> {
            while (!socketCommunicationThread.isInterrupted()) {
                if (connection.isConnected() && connection.isAvailable()) {
                    MessageDto inputDto = connection.getDtoAction();
                    if (inputDto.getStatus() == Status.MESSAGE) {
                        chatMonitor.addMessage(inputDto.getLogin(), inputDto.getMessage());
                    }
                }

            }
        });
        socketCommunicationThread.start();
    }

}
