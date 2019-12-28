package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.event.TimeChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.view.iconService.IconStorage;

public class TimerLabel extends NumericInfoLabel {

    public TimerLabel(IObserverManager observerManager) {
        observerManager.addObserver(TimeChangeEvent.class, event -> setValue(event.getCurrentTime()));
        setImageIconAndResize(IconStorage.TIME.getImageIcon());
    }
}
