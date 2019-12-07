package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
class Producer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final Storage storage;
    private final Integer produceTime;

    @Override
    public void run() {
        while (true) {
            logger.info("Producer: " + Thread.currentThread().getName() + " проснулся.");
            Resource resource = new Resource();
            if (storage.add(resource)) {
                logger.info("Producer: " + Thread.currentThread().getName() + " создал ресурс: " + resource.getId() + ".");
            }
            try {
                Thread.sleep(produceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
