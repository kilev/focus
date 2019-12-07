package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class Storage {

    private static final Logger logger = LoggerFactory.getLogger(Storage.class);

    private static final int MIN_RESOURCE_COUNT = 0;
    private final int MAX_RESOURCE_COUNT;

    private final List<Resource> resources = new ArrayList<>();

    private int resourceCount = 0;

    synchronized boolean add(Resource resource) {
        try {
            if (resourceCount < MAX_RESOURCE_COUNT) {
                notifyAll();
                resources.add(resource);
                resourceCount++;
                logger.info("Ресурс с id: " + resource.getId() + " добавлен на склад producer'ом: " + Thread.currentThread().getName());
                return true;
            } else {
                wait();
                logger.info("Склад переполнен, producer " + Thread.currentThread().getName() + " не смог поместить ресурс (уснул).");
            }
        } catch (InterruptedException e) {
            logger.error("ошибка", e);
        }
        return false;
    }

    synchronized Resource get() {
        try {
            if (resourceCount > MIN_RESOURCE_COUNT) {
                notifyAll();
                Resource resource = resources.get(0);
                resources.remove(0);
                resourceCount--;
                logger.info("Ресурс с id: " + resource.getId() + " забрал consumer: " + Thread.currentThread().getName());
                return resource;
            }
            logger.info("Склад пуст, consumer " + Thread.currentThread().getName() + " не смог взять ресурс (уснул).");
            wait();
        } catch (InterruptedException e) {
            logger.error("Ошибка.", e);
        }
        return null;
    }
}
