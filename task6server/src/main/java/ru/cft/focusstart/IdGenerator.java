package ru.cft.focusstart;

import java.util.concurrent.atomic.AtomicLong;

class IdGenerator {

    private final AtomicLong id = new AtomicLong(0);

    long getId() {
        return id.getAndIncrement();
    }
}
