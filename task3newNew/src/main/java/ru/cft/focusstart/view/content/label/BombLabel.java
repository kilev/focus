package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.event.BombCountChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.view.iconService.IconStorage;

public class BombLabel extends NumericInfoLabel {

    public BombLabel(IObserverManager observerManager) {
        observerManager.addObserver(BombCountChangeEvent.class, event -> setValue(event.getBombCount()));
        setImageIconAndResize(IconStorage.BOMB.getImageIcon());
    }

}
