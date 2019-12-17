package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

public interface Observer<T extends Event> {

    void handleEvent(T event);

    default void handleDto(Event event) {
        handleEvent((T) event);
    }
}
