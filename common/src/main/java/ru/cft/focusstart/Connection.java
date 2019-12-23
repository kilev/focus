package ru.cft.focusstart;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.dto.Dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class Connection {

    private static final String UN_AUTHORIZED_LOGIN = "anonymous";

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

    public void sendDto(Dto dto) {
        try {
            writer.println(new ObjectMapper().writeValueAsString(dto));
            log.info("Sended dto: {}", dto);
        } catch (IOException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    public void callRequestAction() {
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

    public void disconnect() {
        if (!socket.isClosed()) {
            try (PrintWriter writer1 = writer; BufferedReader reader1 = reader; Socket socket1 = socket) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            connectionListener.onDisconnect(this);
        }
    }

    public boolean isAvailable() {
        try {
            return reader.ready();
        } catch (IOException e) {
            disconnect();
            connectionListener.onException(this, e);
            return false;
        }
    }

    public boolean isAuthorized() {
        return login != null;
    }

    public boolean isExpired() {
        if (nonActivityConnectionLiveTime == null) {
            return false;
        } else {
            return System.currentTimeMillis() - lastActivityTime > nonActivityConnectionLiveTime;
        }
    }

    private void updateActivity() {
        lastActivityTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        if (isAuthorized()) {
            return login;
        } else {
            return UN_AUTHORIZED_LOGIN;
        }
    }
}