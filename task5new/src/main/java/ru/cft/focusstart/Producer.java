package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Producer implements Runnable {

    private static final Integer PRODUCE_TIME = PropertyManager.PRODUCE_TIME.getValue();
    private final Storage<Task> storage;

    @Override
    public void run() {
        while (true) {
            Task resource = new Task();
            log.info("Producer: " + Thread.currentThread().getName() + " произвел ресурс: " + resource.getId() + ".");
            storage.add(resource);
            log.info("Producer: " + Thread.currentThread().getName() + " поместил: " + resource.getId() + ".");
            try {
                Thread.sleep(PRODUCE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
