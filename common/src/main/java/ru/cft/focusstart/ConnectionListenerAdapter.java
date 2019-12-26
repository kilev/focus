package ru.cft.focusstart;

import ru.cft.focusstart.dto.*;

public class ConnectionListenerAdapter implements ConnectionListener {

    @Override
    public void onMessage(MessageDto messageDto, Connection connection) {

    }

    @Override
    public void onLoginRequest(LoginRequestDto loginRequestDto, Connection connection) {

    }

    @Override
    public void onLoginResponse(LoginResponseDto loginResponseDto, Connection connection) {

    }

    @Override
    public void onUserOnline(UserOnlineDto userOnlineDto, Connection connection) {

    }

    @Override
    public void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection) {

    }

    @Override
    public void onDisconnect(Connection connection) {

    }

    @Override
    public void onException(Connection connection, Exception e) {

    }
}
