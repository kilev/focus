package ru.cft.focusstart.dto;

import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

/**
 * В отличие от простого PingDto, получения CallBackPingDto
 * подрузамевает что вы подадите ответную реакцию отправителю,
 * тем самым подав знак, что вы живы.
 */

public class CallBackPingDto implements Dto {

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onCallBackPing(this, connection);
    }
}
