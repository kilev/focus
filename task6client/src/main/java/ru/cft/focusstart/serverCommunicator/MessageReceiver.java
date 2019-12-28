package ru.cft.focusstart.serverCommunicator;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.dto.BroadCastMessageDto;
import ru.cft.focusstart.view.MainView;

import java.util.concurrent.*;

/**
 * Согласование последовательности сообщений реализовано на клиенте.
 * Это снимает данную обязанность с сервера, делая его более легковесным,
 * что повысит его пропускную способность.
 * Логика упорядочивания сообщений устроена следующим образом:
 * клиент запоминает последний id сообщения
 * (относится к сообщениям с тектом от других пользователей)
 * от сервера, в случае если нам пришло какое-либо письмо раньше
 * того, которое мы ждем, то мы его "замораживаем"
 * и ждем нужное. Для каждого замороженного письма существует таймаут,
 * который мониторит отдельный поток.
 * Если таймаут превышен, сообщение все равно будет показано пользователю,
 * условно считая, что нужное сообщение мы не дождемся.
 */

@RequiredArgsConstructor
class MessageReceiver {

    private static final int FROZEN_MESSAGE_QUEUE_CAPACITY = 10;
    private static final int FREEZE_MESSAGE_TIMEOUT_MILLIS = 1000;
    private static final int CHECK_FROZEN_MESSAGE_TIMEOUT_MILLIS = 400;

    private final BlockingQueue<FrozenMessage> frozenMessages = new PriorityBlockingQueue<>(FROZEN_MESSAGE_QUEUE_CAPACITY,
            (o1, o2) -> o2.getMessageDto().getId().compareTo(o1.getMessageDto().getId()));
    private final MainView view;
    private Long lastMessageId = null;
    private ScheduledExecutorService frozenTimeCheckService;

    void start() {
        frozenTimeCheckService = Executors.newSingleThreadScheduledExecutor();
        frozenTimeCheckService.scheduleAtFixedRate(() -> frozenMessages.stream()
                .filter(frozenMessage -> frozenMessage.getFreezeTime() + FREEZE_MESSAGE_TIMEOUT_MILLIS > System.currentTimeMillis())
                .peek(frozenMessage -> updateId(frozenMessage.getMessageDto().getId()))
                .forEach(frozenMessage -> sendToView(frozenMessage.getMessageDto())), 0, CHECK_FROZEN_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    }

    void stop() {
        lastMessageId = null;
        frozenTimeCheckService.shutdown();
    }

    void receive(BroadCastMessageDto messageDto) {
        if (checkSequence(messageDto.getId())) {
            updateId(messageDto.getId());
            sendToView(messageDto);
            checkFrozenMessages();
        } else {
            frozenMessages.add(new FrozenMessage(System.currentTimeMillis(), messageDto));
        }
    }

    private boolean checkSequence(Long messageId) {
        if (lastMessageId == null) {
            lastMessageId = messageId;
            return true;
        }
        return messageId - 1 == lastMessageId;
    }

    private void updateId(Long messageId) {
        lastMessageId = messageId;
    }

    private void checkFrozenMessages() {
        frozenMessages.stream()
                .filter(frozenMessage -> checkSequence(frozenMessage.getMessageDto().getId()))
                .peek(frozenMessage -> updateId(frozenMessage.getMessageDto().getId()))
                .forEach(frozenMessage -> sendToView(frozenMessage.getMessageDto()));
    }

    private void sendToView(BroadCastMessageDto messageDto) {
        view.addMessage(messageDto.getAuthor(), messageDto.getMessage(), messageDto.getTime());
    }
}
