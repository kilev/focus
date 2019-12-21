package ru.cft.focusstart.dto;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@RequiredArgsConstructor
public class LoginResponseDto implements Dto {

    private final boolean confirmed;

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection socketConnection) {
        connectionListener.onLoginResponse(confirmed);
    }
}
