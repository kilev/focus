package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.dto.MessageDto;
import ru.cft.focusstart.view.MainView;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class MessageReceiver {
    private final List<MessageDto> frozenMessages = new ArrayList<>();
    private final MainView view;
    private long lastMessageId = 0;

    void receive(MessageDto messageDto) {
        if (checkSequence(messageDto)) {
            updateId(messageDto);
            sendToView(messageDto);
            checkFrozenMessages();
        } else {
            frozenMessages.add(messageDto);
        }
    }

    private boolean checkSequence(MessageDto messageDto) {
        return messageDto.getId() - 1 == lastMessageId;
    }

    private void updateId(MessageDto messageDto) {
        lastMessageId = messageDto.getId();
    }

    private void checkFrozenMessages() {
        frozenMessages.stream()
                .filter(this::checkSequence)
                .peek(this::updateId)
                .forEach(this::sendToView);
    }

    private void sendToView(MessageDto messageDto) {
        view.addMessage(messageDto.getAuthor(), messageDto.getMessage());
    }
}
