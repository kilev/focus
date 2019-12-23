package ru.cft.focusstart;

import java.util.List;

public class ConnectionListenerAdapter implements ConnectionListener {

    @Override
    public void onMessage(String login, String message) {

    }

    @Override
    public void onLoginRequest(String login, Connection connection) {

    }

    @Override
    public void onLoginResponse(String login, boolean confirmed) {

    }

    @Override
    public void onUserInOnlineList(List<String> userInOnlineList) {

    }

    @Override
    public void onDisconnectRequest(Connection connection) {

    }

    @Override
    public void onDisconnect(Connection connection) {

    }

    @Override
    public void onException(Connection connection, Exception e) {

    }
}
