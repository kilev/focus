package ru.cft.focusstart.dto;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@RequiredArgsConstructor
public class LoginRequestDto implements Dto {

    private final String login;

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection socketConnection) {
        connectionListener.onLoginRequest(login, socketConnection);
    }
}
