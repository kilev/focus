package ru.cft.focusstart.dto;

import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class DisconnectRequestDto implements Dto {

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onDisconnectRequest(this, connection);
    }
}
