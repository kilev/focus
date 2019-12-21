package ru.cft.focusstart;

import com.google.gson.Gson;
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
public class SocketConnection {

    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Long nonActivityConnectionLiveTime;
    private final ConnectionListener connectionListener;
    private volatile long lastActivityTime;
    @Getter
    @Setter
    private volatile String login;

    public SocketConnection(String host, Integer port, Long nonActivityConnectionLiveTime, ConnectionListener connectionListener) throws IOException {
        this(new Socket(host, port), nonActivityConnectionLiveTime, connectionListener);
    }

    public SocketConnection(Socket socket, Long nonActivityConnectionLiveTime, ConnectionListener connectionListener) throws IOException {
        this.socket = socket;
        this.nonActivityConnectionLiveTime = nonActivityConnectionLiveTime;
        this.connectionListener = connectionListener;
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        lastActivityTime = System.currentTimeMillis();
    }

    public void sendDto(Dto dto) {
        writer.println(new Gson().toJson(dto));
    }

    public Dto getDtoAction() {
        try {
            Dto dto = new Gson().fromJson(reader.readLine(), Dto.class);
            //parsing dto

            updateActivity();
            return dto;
        } catch (IOException e) {
            disconnect();
            connectionListener.onException(this, e);
            return null;
        }
    }

    public void disconnect() {
        try {
            writer.close();
            reader.close();
            socket.close();
            connectionListener.onDisconnect(this);
        } catch (IOException e) {
            connectionListener.onException(this, e);
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

    public boolean isConnected() {
        return socket.isConnected();
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
            return "anonymous";
        }
    }
}
