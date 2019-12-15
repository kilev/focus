package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Consumer implements Runnable {

    private static final Integer CONSUME_TIME = PropertyManager.CONSUME_TIME.getValue();
    private final Storage<Task> storage;

    @Override
    public void run() {
        while (true) {
            Task resource = storage.get();
            log.info("Consumer: " + Thread.currentThread().getName() + " забрал ресурс " + resource.getId() + ".");
            try {
                Thread.sleep(CONSUME_TIME);
                log.info("Consumer: " + Thread.currentThread().getName() + " потребил ресурс: " + resource.getId() + ".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
