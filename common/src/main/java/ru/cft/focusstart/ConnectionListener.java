package ru.cft.focusstart;

import java.util.List;

public interface ConnectionListener {

    void onMessage(String login, String message);

    void onLoginRequest(String login, Connection connection);

    void onLoginResponse(String login, boolean confirmed);

    void onUserInOnlineList(List<String> userInOnlineList);

    void onDisconnectRequest(Connection connection);

    void onDisconnect(Connection connection);

    void onException(Connection connection, Exception e);
}
