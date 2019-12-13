package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
class Storage {

    private static final int MIN_RESOURCE_COUNT = 0;
    private final Integer maxResourceCount;

    private final List<Resource> resources = new ArrayList<>();

    synchronized void add(Resource resource) {
        try {
            if (resources.size() < maxResourceCount) {
                notifyAll();
                resources.add(resource);
                log.info("Ресурс с id: " + resource.getId() + " добавлен на склад producer'ом: " + Thread.currentThread().getName());
            } else {
                log.info("Склад переполнен, producer " + Thread.currentThread().getName() + " не смог поместить ресурс (уснул).");
                wait();
                log.info("Producer: " + Thread.currentThread().getName() + " проснулся.");
            }
        } catch (InterruptedException e) {
            log.error("Остановка потока.", e);
        }
    }

    synchronized Resource get() {
        try {
            if (resources.size() > MIN_RESOURCE_COUNT) {
                notifyAll();
                Resource resource = resources.get(0);
                resources.remove(0);
                log.info("Ресурс с id: " + resource.getId() + " забрал consumer: " + Thread.currentThread().getName());
                return resource;
            }
            log.info("Склад пуст, consumer " + Thread.currentThread().getName() + " не смог взять ресурс (уснул).");
            wait();
            log.info("Consumer: " + Thread.currentThread().getName() + " проснулся.");
        } catch (InterruptedException e) {
            log.error("Остановка потока.", e);
        }
        return null;
    }
}
