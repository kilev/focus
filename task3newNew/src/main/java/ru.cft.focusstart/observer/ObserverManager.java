package ru.cft.focusstart.observer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.cft.focusstart.dto.EventDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ObserverManager implements IObserverManager {

    private final Map<Class<? extends EventDto>, List<Observer<? extends EventDto>>> observers = new HashMap<>();

    @Inject
    public ObserverManager() {
    }

    @Override
    public void addObserver(Class<? extends EventDto> dtoClass, Observer<? extends EventDto> observer) {
        final List<Observer<? extends EventDto>> listForClass = observers.getOrDefault(dtoClass, new ArrayList<>());
        listForClass.add(observer);
        observers.put(dtoClass, listForClass);
    }

    @Override
    public void notifyObservers(EventDto dto) {
        observers.get(dto.getClass()).forEach(observer -> observer.handleDto(dto));
    }
}
