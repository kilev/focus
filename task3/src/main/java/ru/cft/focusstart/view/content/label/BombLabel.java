package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observer;
import ru.cft.focusstart.dto.BombCountChangeDTO;
import ru.cft.focusstart.view.iconService.iconV2.IconStorage;

public class BombLabel extends NumericInfoLabel implements Observer<BombCountChangeDTO> {

    public BombLabel(IObserverManager observerManager) {
        observerManager.addObserver(BombCountChangeDTO.class, this);
        setImageIconAndResize(IconStorage.BOMB.getImageIcon());
    }

    @Override
    public void handleEvent(BombCountChangeDTO bombCountChangeDTO) {
        setValue(bombCountChangeDTO.getBombCount());
    }
}
