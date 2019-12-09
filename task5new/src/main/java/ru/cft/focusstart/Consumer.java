package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Consumer implements Runnable {

    private final Storage storage;
    private final Integer consumeTime;

    @Override
    public void run() {
        while (true) {
            Resource resource = storage.get();
            if (resource != null) {
                try {
                    Thread.sleep(consumeTime);
                    log.info("Consumer:" + Thread.currentThread().getName() + " потребил ресурс: " + resource.getId() + ".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
