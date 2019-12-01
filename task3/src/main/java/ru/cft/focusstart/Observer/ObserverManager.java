package ru.cft.focusstart.Observer;

import ru.cft.focusstart.dto.EventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObserverManager implements IObserverManager {

    private final Map<Class, List<Observer<? extends EventDto>>> observers = new HashMap<>();

    @Override
    public void addObserver(Class dtoClass, Observer<? extends EventDto> observer) {
        final List<Observer<? extends EventDto>> listForClass = observers.getOrDefault(dtoClass, new ArrayList<>());
        listForClass.add(observer);
        observers.put(dtoClass, listForClass);
    }

    @Override
    public void notifyObservers(EventDto dto) {
        observers.get(dto.getClass()).forEach(observer -> observer.handleDto(dto));
    }
}
