package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class LoginRequestDto implements Dto {

    private final String login;

    public LoginRequestDto(@JsonProperty("login") String login) {
        this.login = login;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onLoginRequest(this, connection);
    }
}
