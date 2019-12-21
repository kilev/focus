package ru.cft.focusstart.dto;

import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

public interface Dto {
    void getDtoAction(ConnectionListener connectionListener, Connection socketConnection);
}
