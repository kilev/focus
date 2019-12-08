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
            Resource resource = new Resource();
            logger.info("Producer: " + Thread.currentThread().getName() + " создал ресурс: " + resource.getId() + ".");
            storage.add(resource);
            try {
                Thread.sleep(produceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
