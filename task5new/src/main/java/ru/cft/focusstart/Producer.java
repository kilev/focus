package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Producer implements Runnable {

    private final Storage storage;
    private final Integer produceTime;

    @Override
    public void run() {
        while (true) {
            Resource resource = new Resource();
            log.info("Producer: " + Thread.currentThread().getName() + " создал ресурс: " + resource.getId() + ".");
            storage.add(resource);
            try {
                Thread.sleep(produceTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
