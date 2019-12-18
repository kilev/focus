package ru.cft.focusstart.view.content.gameField;

import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.event.CellChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.view.content.Resizable;
import ru.cft.focusstart.view.iconService.IconStorage;

import javax.swing.*;

@Getter
@Setter
public class ViewCell extends JButton implements Resizable {

    private final Integer coordX;
    private final Integer coordY;

    private ImageIcon ImageIcon;
    private Integer imageIconSize = 30;

    ViewCell(IObserverManager observerManager, Integer coordX, Integer coordY) {
        this.coordX = coordX;
        this.coordY = coordY;

        observerManager.addObserver(CellChangeEvent.class, event -> {
            if (!coordX.equals(event.getX()) || !coordY.equals(event.getY())) {
                return;
            }
            switch (event.getCellType()) {
                case CLOSED:
                    setImageIconAndResize(IconStorage.CLOSED.getImageIcon());
                    break;
                case FLAGGED:
                    setImageIconAndResize(IconStorage.FLAGED.getImageIcon());
                    break;
                case OPENED:
                    setImageIconAndResize(IconStorage.valueOf(IconStorage.OPENED_NUMBER_PREFIX + event.getBombsAround().toString()).getImageIcon());
                    break;
                case EXPLODED:
                    setImageIconAndResize(IconStorage.BOMB.getImageIcon());
            }
        });
    }

}
