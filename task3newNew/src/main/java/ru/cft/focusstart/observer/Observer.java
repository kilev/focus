package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

@FunctionalInterface
public interface Observer<T extends Event> {

    void handleEvent(T event);
}
