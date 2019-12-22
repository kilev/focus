package ru.cft.focusstart;

public interface ConnectionListener {

    void onMessage(String login, String message);

    void onLoginRequest(String login, Connection connection);

    void onLoginResponse(String login, boolean confirmed);

    void onDisconnect(Connection connection);

    void onException(Connection connection, Exception e);
}
