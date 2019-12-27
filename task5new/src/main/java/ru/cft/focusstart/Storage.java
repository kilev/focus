package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class Storage {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition availableToTakeCondition = lock.newCondition();
    private final Condition availableToStoreCondition = lock.newCondition();
    private final BlockingQueue<Resource> resources = new LinkedBlockingQueue<>(PropertyManager.STORAGE_SIZE.getValue());

    void add(Resource resource) {
        lock.lock();
        try {
            while (!resources.offer(resource)) {
                log.info("склад полон, поток переходит в режим ожидания.");
                availableToStoreCondition.await();
                log.info("вышел из режима ожидания.");
            }
            log.info("поместил: {}.", resource.getId());
            availableToTakeCondition.signal();
        } catch (InterruptedException e) {
            log.error("Поток остановлен.", e);
        } finally {
            lock.unlock();
        }
    }

    Resource get() {
        lock.lock();
        try {
            Resource resource = resources.poll();
            while (resource == null) {
                log.info("склад пуст, поток переходит в режим ожидания.");
                availableToTakeCondition.await();
                log.info("вышел из режима ожидания.");
                resource = resources.poll();
            }
            log.info("забрал ресурс {}.", resource.getId());
            availableToStoreCondition.signal();
            return resource;
        } catch (InterruptedException e) {
            log.error("Поток остановлен.", e);
        } finally {
            lock.unlock();
        }
        return null;
    }

}
