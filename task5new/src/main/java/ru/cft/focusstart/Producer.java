package ru.cft.focusstart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class Producer implements Runnable {

    @Getter
    private static final String NAME = "Producer";
    private static final Integer PRODUCE_TIME = PropertyManager.PRODUCE_TIME.getValue();

    private final Storage storage;

    @Override
    public void run() {
        while (true) {
            try {
                Resource resource = new Resource();
                log.info("произвел ресурс: {}.", resource.getId());
                storage.add(resource);
                Thread.sleep(PRODUCE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
