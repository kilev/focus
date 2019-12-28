package ru.cft.focusstart.model.cell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.event.CellChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observable;

@Getter
@Setter
@AllArgsConstructor
public class CellModel implements Observable {

    private final IObserverManager observerManager;

    private CellType cellType;
    private Boolean bomb;
    private Integer bombAround;

    private Integer x;
    private Integer y;

    public void open() {
        if (cellType == CellType.CLOSED) {
            if (bomb) {
                cellType = CellType.EXPLODED;
            } else {
                cellType = CellType.OPENED;
            }
            notifyObservers();
        }
    }

    public void flag() {
        if (cellType == CellType.CLOSED) {
            cellType = CellType.FLAGGED;
            notifyObservers();
        } else if (cellType == CellType.FLAGGED) {
            cellType = CellType.CLOSED;
            notifyObservers();
        }
    }

    @Override
    public void notifyObservers() {
        observerManager.notifyObservers(new CellChangeEvent(cellType, x, y, bombAround));
    }
}
