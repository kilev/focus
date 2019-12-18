package ru.cft.focusstart.observer;

import com.google.inject.Singleton;
import ru.cft.focusstart.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ObserverManager implements IObserverManager {

    private final Map<Class<? extends Event>, List<Observer<? extends Event>>> observers = new HashMap<>();

    @Override
    public <T extends Event> void addObserver(Class<T> eventClass, Observer<T> observer) {
        final List<Observer<? extends Event>> listForClass = observers.getOrDefault(eventClass, new ArrayList<>());
        listForClass.add(observer);
        observers.put(eventClass, listForClass);
    }

    @Override
    public <T extends Event> void notifyObservers(T event) {
        observers.get(event.getClass()).forEach(observer -> ((Observer<T>) observer).handleEvent(event));
    }
}
