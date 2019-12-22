package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class MessageDto implements Dto {

    private final String login;
    private final String message;

    public MessageDto(@JsonProperty("login") String login, @JsonProperty("message") String message) {
        this.login = login;
        this.message = message;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection socketConnection) {
        connectionListener.onMessage(login, message);
    }
}
