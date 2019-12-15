package ru.cft.focusstart;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

class Task {

    private static final AtomicInteger count = new AtomicInteger(0);
    @Getter
    private final long id;

    Task() {
        id = count.getAndIncrement();
    }
}
