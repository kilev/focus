package ru.cft.focusstart.Observer;

import ru.cft.focusstart.dto.EventDto;

public interface IObserverManager {

    void addObserver(Class dtoClass, Observer<? extends EventDto> observer);

    void notifyObservers(EventDto dto);
}
