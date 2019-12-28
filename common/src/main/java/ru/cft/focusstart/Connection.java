package ru.cft.focusstart;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.CallBackPingDto;
import ru.cft.focusstart.dto.Dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class Connection {

    private static final String UN_AUTHORIZED_LOGIN = "Anonymous";
    private static final int CALLBACK_PING_TIMEOUT = 1000;

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Long nonActivityConnectionLiveTime;
    private final ConnectionListener connectionListener;
    private long lastActivityTime;
    @Getter
    @Setter
    private String login;

    public Connection(String host, Integer port, Long nonActivityConnectionLiveTime, ConnectionListener connectionListener) throws IOException {
        this(new Socket(host, port), nonActivityConnectionLiveTime, connectionListener);
    }

    public Connection(Socket socket, Long nonActivityConnectionLiveTime, ConnectionListener connectionListener) throws IOException {
        this.socket = socket;
        this.nonActivityConnectionLiveTime = nonActivityConnectionLiveTime;
        this.connectionListener = connectionListener;
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        lastActivityTime = System.currentTimeMillis();
    }

    public Connection reconnect() throws IOException {
        disconnect();
        return new Connection(socket.getInetAddress().getHostAddress(), socket.getPort(), nonActivityConnectionLiveTime, connectionListener);

    }

    public synchronized void sendDto(Dto dto) {
        try {
            writer.println(new ObjectMapper().writeValueAsString(dto));
            log.info("Sended dto: {}", dto);
        } catch (IOException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    public synchronized void callRequestAction() {
        if (isAvailable()) {
            try {
                Dto readedDto = new ObjectMapper().readValue(reader.readLine(), Dto.class);
                readedDto.getDtoAction(connectionListener, this);
                log.info("readed dto: {}", readedDto);
                updateActivity();
            } catch (IOException | IllegalArgumentException e) {
                disconnect();
                connectionListener.onException(this, e);
            }
        }
    }

    public synchronized void disconnect() {
        if (!socket.isClosed()) {
            //noinspection EmptyTryBlock
            try (PrintWriter writer1 = writer; BufferedReader reader1 = reader; Socket socket1 = socket) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            connectionListener.onDisconnect(this);
        }
    }

    public synchronized boolean isAvailable() {
        try {
            return reader.ready();
        } catch (IOException e) {
            disconnect();
            connectionListener.onException(this, e);
            return false;
        }
    }

    public synchronized void checkConnection() {
        long time = System.currentTimeMillis();
        sendDto(new CallBackPingDto());
        try {
            Thread.sleep(CALLBACK_PING_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (time > lastActivityTime) {
            disconnect();
        }
    }

    public synchronized boolean isAuthorized() {
        return login != null;
    }

    public synchronized boolean isExpired() {
        if (nonActivityConnectionLiveTime == null) {
            return false;
        } else {
            return System.currentTimeMillis() - lastActivityTime > nonActivityConnectionLiveTime;
        }
    }

    private synchronized void updateActivity() {
        lastActivityTime = System.currentTimeMillis();
    }

    @Override
    public synchronized String toString() {
        if (isAuthorized()) {
            return login;
        } else {
            return UN_AUTHORIZED_LOGIN;
        }
    }
}
