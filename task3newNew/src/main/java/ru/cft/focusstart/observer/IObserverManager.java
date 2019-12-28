package ru.cft.focusstart.observer;

import ru.cft.focusstart.event.Event;

public interface IObserverManager {

    <T extends Event> void addObserver(Class<T> eventClass, Observer<T> observer);

    <T extends Event> void notifyObservers(T event);
}
