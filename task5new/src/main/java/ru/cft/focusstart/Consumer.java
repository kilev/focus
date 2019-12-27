package ru.cft.focusstart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Consumer implements Runnable {

    @Getter
    private static final String NAME = "Consumer";
    private static final Integer CONSUME_TIME = PropertyManager.CONSUME_TIME.getValue();

    private final Storage storage;

    @Override
    public void run() {
        while (true) {
            try {
                Resource resource = storage.get();
                Thread.sleep(CONSUME_TIME);
                log.info("потребил ресурс: {}.", resource.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
