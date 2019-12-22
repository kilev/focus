package ru.cft.focusstart;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Long nonActivityConnectionLiveTime;
    private final ConnectionListener connectionListener;
    private volatile long lastActivityTime;
    @Getter
    @Setter
    private volatile String login;

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

    public synchronized void sendDto(Dto dto) {
        try {
            writer.println(new ObjectMapper().writeValueAsString(dto));
            log.info("Sended dto: {}", dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getDtoAction() {
        try {
            Dto dtoToRead = new ObjectMapper().readValue(reader.readLine(), Dto.class);
            dtoToRead.getDtoAction(connectionListener, this);
            log.info("readed dto: {}", dtoToRead);
            updateActivity();
        } catch (IOException e) {
            disconnect();
            connectionListener.onException(this, e);
        }
    }

    public synchronized void disconnect() {
        if (!socket.isClosed()) {
            try {
                writer.close();
                reader.close();
                socket.close();
                connectionListener.onDisconnect(this);
            } catch (IOException e) {
                connectionListener.onException(this, e);
            }
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

    private void updateActivity() {
        lastActivityTime = System.currentTimeMillis();
    }

    @Override
    public synchronized String toString() {
        if (isAuthorized()) {
            return login;
        } else {
            return "anonymous";
        }
    }
}
