package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.dto.BombCountChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.iconService.IconStorage;

public class BombLabel extends NumericInfoLabel implements Observer<BombCountChangeDto> {

    public BombLabel(IObserverManager observerManager) {
        observerManager.addObserver(BombCountChangeDto.class, this);
        setImageIconAndResize(IconStorage.BOMB.getImageIcon());
    }

    @Override
    public void handleEvent(BombCountChangeDto bombCountChangeDTO) {
        setValue(bombCountChangeDTO.getBombCount());
    }
}
