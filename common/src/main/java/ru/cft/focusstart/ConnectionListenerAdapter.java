package ru.cft.focusstart;

public class ConnectionListenerAdapter implements ConnectionListener {

    @Override
    public void onMessage(String login, String message) {

    }

    @Override
    public void onLoginRequest(String login, Connection connection) {

    }

    @Override
    public void onLoginResponse(boolean confirmed) {

    }

    @Override
    public void onDisconnect(Connection connection) {

    }

    @Override
    public void onException(Connection connection, Exception e) {

    }
}
