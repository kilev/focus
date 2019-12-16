package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.event.TimeChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.iconService.IconStorage;

public class TimerLabel extends NumericInfoLabel implements Observer<TimeChangeEvent> {

    public TimerLabel(IObserverManager observerManager) {
        observerManager.addObserver(TimeChangeEvent.class, this);
        setImageIconAndResize(IconStorage.TIME.getImageIcon());
    }

    @Override
    public void handleEvent(TimeChangeEvent timeChanchedDTO) {
        setValue(timeChanchedDTO.getCurrentTime());
    }
}
