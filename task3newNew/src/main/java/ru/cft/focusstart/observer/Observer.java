package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

public interface Observer<T extends Event> {

    void handleEvent(T dto);

    default void handleDto(Event dto) {
        handleEvent((T) dto);
    }
}
