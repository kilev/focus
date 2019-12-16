package ru.cft.focusstart;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

class Resource {

    private static final AtomicInteger count = new AtomicInteger(0);
    @Getter
    private final long id;

    Resource() {
        id = count.getAndIncrement();
    }
}
