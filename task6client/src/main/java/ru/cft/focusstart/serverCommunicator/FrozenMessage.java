package ru.cft.focusstart.serverCommunicator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.dto.BroadCastMessageDto;

@Getter
@RequiredArgsConstructor
class FrozenMessage {

    private final long freezeTime;
    private final BroadCastMessageDto messageDto;
}
