package ru.cft.focusstart.view.content.gameField;

import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.dto.CellChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.content.Resizable;
import ru.cft.focusstart.view.iconService.IconStorage;

import javax.swing.*;

@Getter
@Setter
public class ViewCell extends JButton implements Observer<CellChangeDto>, Resizable {

    private final Integer coordX;
    private final Integer coordY;

    private ImageIcon ImageIcon;
    private Integer imageIconSize = 30;

    ViewCell(IObserverManager observerManager, Integer coordX, Integer coordY) {
        this.coordX = coordX;
        this.coordY = coordY;

        observerManager.addObserver(CellChangeDto.class, this);
    }

    @Override
    public void handleEvent(CellChangeDto cellChangeDTO) {
        if (!coordX.equals(cellChangeDTO.getX()) || !coordY.equals(cellChangeDTO.getY())) {
            return;
        }
        switch (cellChangeDTO.getCellType()) {
            case CLOSED:
                setImageIconAndResize(IconStorage.CLOSED.getImageIcon());
                break;
            case FLAGGED:
                setImageIconAndResize(IconStorage.FLAGED.getImageIcon());
                break;
            case OPENED:
                setImageIconAndResize(IconStorage.valueOf(IconStorage.OPENED_NUMBER_PREFIX + cellChangeDTO.getBombsAround().toString()).getImageIcon());
                break;
            case EXPLODED:
                setImageIconAndResize(IconStorage.BOMB.getImageIcon());
        }
    }
}
