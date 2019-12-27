package ru.cft.focusstart;

import ru.cft.focusstart.dto.*;

public interface ConnectionListener {

    void onBroadcastMessage(BroadCastMessageDto messageDto, Connection connection);

    void onClientMessage(ClientMessageDto clientMessageDto, Connection connection);

    void onLoginRequest(LoginRequestDto loginRequestDto, Connection connection);

    void onLoginReconnect(LoginReconnectDto loginReconnectDto, Connection connection);

    void onLoginResponse(LoginResponseDto loginResponseDto, Connection connection);

    void onUserOnline(UserOnlineDto userOnlineDto, Connection connection);

    void onDisconnectRequest(DisconnectRequestDto disconnectRequestDto, Connection connection);

    void onPing(PingDto pingDto, Connection connection);

    void onCallBackPing(CallBackPingDto callBackPingDto, Connection connection);

    void onDisconnect(Connection connection);

    void onException(Connection connection, Exception e);
}
