package ru.cft.focusstart.dto;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;


@RequiredArgsConstructor
public class MessageDto implements Dto {

    private final String login;
    private final String message;

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection socketConnection) {
        connectionListener.onMessage(login, message);
    }
}
