package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class Storage {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition nonEmpty = lock.newCondition();
    private final Condition nonFull = lock.newCondition();
    private final BlockingQueue<Resource> resources = new LinkedBlockingQueue<>(PropertyManager.STORAGE_SIZE.getValue());

    void add(Resource resource) {
        lock.lock();
        try {
            while (!resources.offer(resource)) {
                log.info("{}: склад полон, поток переходит в режим ожидания.", Thread.currentThread().getName());
                nonFull.await();
                log.info("{}: вышел из режима ожидания.", Thread.currentThread().getName());
            }
            log.info("{}: поместил: {}.", Thread.currentThread().getName(), resource.getId());
            nonEmpty.signal();
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
                log.info("{}: склад пуст, поток переходит в режим ожидания.", Thread.currentThread().getName());
                nonEmpty.await();
                log.info("{}: вышел из режима ожидания.", Thread.currentThread().getName());
                resource = resources.poll();
            }
            log.info("{}: забрал ресурс {}.", Thread.currentThread().getName(), resource.getId());
            nonFull.signal();
            return resource;
        } catch (InterruptedException e) {
            log.error("Поток остановлен.", e);
        } finally {
            lock.unlock();
        }
        return null;
    }

}
