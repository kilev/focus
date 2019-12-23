package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class LoginResponseDto implements Dto {

    private final String login;
    private final boolean accepted;

    public LoginResponseDto(@JsonProperty("login") String login, @JsonProperty("accepted") boolean accepted) {
        this.login = login;
        this.accepted = accepted;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onLoginResponse(login, accepted);
    }
}
