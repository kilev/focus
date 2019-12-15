package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
class Storage<T> {

    private final BlockingQueue<T> resources = new ArrayBlockingQueue<>(PropertyManager.STORAGE_SIZE.getValue());

    void add(T resource) {
        if (!resources.offer(resource)) {
            log.info("Producer: " + Thread.currentThread().getName() + " склад полон, поток переходит в режим ожидания.");
            try {
                resources.put(resource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Producer: " + Thread.currentThread().getName() + " вышел из режима ожидания.");
        }
    }

    T get() {
        T resource = resources.poll();
        if (resource == null) {
            log.info("Consumer:" + Thread.currentThread().getName() + " склад пуст, поток переходит в режим ожидания.");
            try {
                resource = resources.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Consumer: " + Thread.currentThread().getName() + " вышел из режима ожидания.");
        }
        return resource;
    }
}
