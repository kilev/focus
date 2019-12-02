package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.dto.TimeChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.iconService.IconStorage;

public class TimerLabel extends NumericInfoLabel implements Observer<TimeChangeDto> {

    public TimerLabel(IObserverManager observerManager) {
        observerManager.addObserver(TimeChangeDto.class, this);
        setImageIconAndResize(IconStorage.TIME.getImageIcon());
    }

    @Override
    public void handleEvent(TimeChangeDto timeChanchedDTO) {
        setValue(timeChanchedDTO.getCurrentTime());
    }
}
