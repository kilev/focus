package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observer;
import ru.cft.focusstart.dto.TimeChancheDTO;
import ru.cft.focusstart.view.iconService.iconV2.IconStorage;

public class TimerLabel extends NumericInfoLabel implements Observer<TimeChancheDTO> {

    public TimerLabel(IObserverManager observerManager) {
        observerManager.addObserver(TimeChancheDTO.class, this);
        setImageIconAndResize(IconStorage.TIME.getImageIcon());
    }

    @Override
    public void handleEvent(TimeChancheDTO timeChanchedDTO) {
        setValue(timeChanchedDTO.getCurrentTime());
    }
}
