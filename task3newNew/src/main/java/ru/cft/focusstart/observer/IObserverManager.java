package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

public interface IObserverManager {

    void addObserver(Class<? extends Event> eventClass, Observer<? extends Event> observer);

    void notifyObservers(Event event);
}
