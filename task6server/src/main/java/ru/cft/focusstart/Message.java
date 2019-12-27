package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.cft.focusstart.dto.BroadCastMessageDto;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Message {

    private static final IdGenerator idGenerator = new IdGenerator();

    static BroadCastMessageDto create(String author, String message) {
        return new BroadCastMessageDto(author, message, idGenerator.getId(), new Date());
    }
}
