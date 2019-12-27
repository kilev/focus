package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class LoginReconnectDto implements Dto {

    private final String login;

    public LoginReconnectDto(@JsonProperty("login") String login) {
        this.login = login;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onLoginReconnect(this, connection);
    }
}
