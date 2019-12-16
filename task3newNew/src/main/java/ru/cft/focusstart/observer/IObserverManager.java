package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

public interface IObserverManager {

    void addObserver(Class<? extends Event> dtoClass, Observer<? extends Event> observer);

    void notifyObservers(Event dto);
}
