package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.event.BombCountChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.iconService.IconStorage;

public class BombLabel extends NumericInfoLabel implements Observer<BombCountChangeEvent> {

    public BombLabel(IObserverManager observerManager) {
        observerManager.addObserver(BombCountChangeEvent.class, this);
        setImageIconAndResize(IconStorage.BOMB.getImageIcon());
    }

    @Override
    public void handleEvent(BombCountChangeEvent bombCountChangeDTO) {
        setValue(bombCountChangeDTO.getBombCount());
    }
}
