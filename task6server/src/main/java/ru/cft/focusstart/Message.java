package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.cft.focusstart.dto.MessageDto;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Message {

    private static final IdGenerator idGenerator = new IdGenerator();

    static MessageDto create(String author, String message) {
        return new MessageDto(author, message, idGenerator.getId(), new Date());
    }
}
