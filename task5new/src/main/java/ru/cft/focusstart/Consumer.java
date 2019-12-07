package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final Storage storage;
    private final Integer consumeTime;

    @Override
    public void run() {
        while (true) {
            logger.info("Consumer: " + Thread.currentThread().getName() + " проснулся.");
            Resource resource = storage.get();
            if (resource != null) {
                try {
                    Thread.sleep(consumeTime);
                    logger.info("Consumer:" + Thread.currentThread().getName() + " потребил ресурс: " + resource.getId() + ".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
