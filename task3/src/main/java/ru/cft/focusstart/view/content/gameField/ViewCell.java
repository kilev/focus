package ru.cft.focusstart.view.content.gameField;

import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observer;
import ru.cft.focusstart.dto.CellChangeDTO;
import ru.cft.focusstart.view.content.Resizable;
import ru.cft.focusstart.view.iconService.iconV2.IconStorage;

import javax.swing.*;

@Getter
@Setter
public class ViewCell extends JButton implements Observer<CellChangeDTO>, Resizable {

    private final Integer coordX;
    private final Integer coordY;

    private ImageIcon ImageIcon;
    private Integer imageIconSize = 30;

    ViewCell(IObserverManager observerManager, Integer coordX, Integer coordY) {
        this.coordX = coordX;
        this.coordY = coordY;

        observerManager.addObserver(CellChangeDTO.class, this);
    }

    @Override
    public void handleEvent(CellChangeDTO cellChangeDTO) {
        if (coordX == cellChangeDTO.getX() && coordY == cellChangeDTO.getY()) {
            switch (cellChangeDTO.getCellType()) {
                case CLOSED:
                    setImageIconAndResize(IconStorage.CLOSED.getImageIcon());
                    break;
                case FLAGED:
                    setImageIconAndResize(IconStorage.FLAGED.getImageIcon());
                    break;
                case OPENED:
                    setImageIconAndResize(IconStorage.valueOf(IconStorage.OPENED_NUMBER_PREFIX + cellChangeDTO.getBombsAround().toString()).getImageIcon());
                    break;
                case BOOMED:
                    setImageIconAndResize(IconStorage.BOMB.getImageIcon());
            }
        }
    }
}
