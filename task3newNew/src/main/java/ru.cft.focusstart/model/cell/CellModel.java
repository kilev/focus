package ru.cft.focusstart.model.cell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.dto.CellChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observered;

@Getter
@Setter
@AllArgsConstructor
public class CellModel implements Observered<CellChangeDto> {

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
            sendDto();
        }
    }

    public void flag() {
        if (cellType == CellType.CLOSED) {
            cellType = CellType.FLAGGED;
            sendDto();
        } else if (cellType == CellType.FLAGGED) {
            cellType = CellType.CLOSED;
            sendDto();
        }
    }

    @Override
    public void sendDto() {
        observerManager.notifyObservers(new CellChangeDto(cellType, x, y, bombAround));
    }
}
