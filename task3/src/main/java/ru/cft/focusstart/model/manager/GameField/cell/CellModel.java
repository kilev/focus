package ru.cft.focusstart.model.manager.GameField.cell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observered;
import ru.cft.focusstart.dto.CellChangeDTO;
import ru.cft.focusstart.model.manager.BombCount.IBombCounter;
import ru.cft.focusstart.model.manager.GameState.GameStateType;
import ru.cft.focusstart.model.manager.GameState.IGameState;

@Getter
@Setter
@AllArgsConstructor
public class CellModel implements Observered<CellChangeDTO> {

    private final IObserverManager observerManager;

    private final IGameState gameState;
    private final IBombCounter bombCounter;

    private CellType cellType;
    private Boolean bomb;
    private Integer bombAround;

    private Integer x;
    private Integer y;

    public void open() {
        if (cellType == CellType.CLOSED) {
            if (bomb) {
                cellType = CellType.BOOMED;
                gameState.setGameState(GameStateType.LOSE);
            } else {
                cellType = CellType.OPENED;
            }
            sendDto();
        }
    }

    public void flag() {
        if (cellType == CellType.CLOSED) {
            cellType = CellType.FLAGED;
            bombCounter.bombFlaged();
            sendDto();
        } else if (cellType == CellType.FLAGED) {
            cellType = CellType.CLOSED;
            bombCounter.bombUnflaged();
            sendDto();
        }
    }

    @Override
    public void sendDto() {
        observerManager.notifyObservers(new CellChangeDTO(cellType, x, y, bombAround));
    }
}
