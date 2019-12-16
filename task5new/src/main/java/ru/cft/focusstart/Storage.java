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
                log.info(Thread.currentThread().getName() + " склад полон, поток переходит в режим ожидания.");
                nonFull.await();
                log.info(Thread.currentThread().getName() + " вышел из режима ожидания.");
            }
            log.info(Thread.currentThread().getName() + " поместил: " + resource.getId() + ".");
            nonEmpty.signalAll();
        } catch (InterruptedException e) {
            log.error("Поток остановлен", e);
        } finally {
            lock.unlock();
        }
    }

    Resource get() {
        lock.lock();
        try {
            Resource resource = resources.poll();
            while (resource == null) {
                log.info(Thread.currentThread().getName() + " склад пуст, поток переходит в режим ожидания.");
                nonEmpty.await();
                log.info(Thread.currentThread().getName() + " вышел из режима ожидания.");
                resource = resources.poll();
            }
            log.info(Thread.currentThread().getName() + " забрал ресурс " + resource.getId() + ".");
            nonFull.signalAll();
            return resource;
        } catch (InterruptedException e) {
            log.error("Поток остановлен", e);
        } finally {
            lock.unlock();
        }
        return null;
    }

}
