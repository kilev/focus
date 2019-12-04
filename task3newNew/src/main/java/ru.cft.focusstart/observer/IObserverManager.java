package ru.cft.focusstart.observer;

import ru.cft.focusstart.dto.EventDto;

public interface IObserverManager {

    void addObserver(Class<? extends EventDto> dtoClass, Observer<? extends EventDto> observer);

    void notifyObservers(EventDto dto);
}
