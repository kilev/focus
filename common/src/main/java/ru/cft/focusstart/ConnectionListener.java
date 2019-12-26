package ru.cft.focusstart;

import ru.cft.focusstart.dto.*;

public interface ConnectionListener {

    void onMessage(MessageDto messageDto, Connection connection);

    void onLoginRequest(LoginRequestDto loginRequestDto, Connection connection);

    void onLoginResponse(LoginResponseDto loginResponseDto, Connection connection);

    void onUserOnline(UserOnlineDto userOnlineDto, Connection connection);

    void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection);

    void onDisconnect(Connection connection);

    void onException(Connection connection, Exception e);
}
