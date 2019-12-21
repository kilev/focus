package ru.cft.focusstart;

public interface ConnectionListener {

    void onMessage(String login, String message);

    void onLoginRequest(SocketConnection socketConnection, String login);

    void onLiginResponse(boolean confirmed);

    void onDisconnectRequest(SocketConnection socketConnection);

    void onDisconnect(SocketConnection socketConnection);

    void onException(SocketConnection socketConnection, Exception e);
}
