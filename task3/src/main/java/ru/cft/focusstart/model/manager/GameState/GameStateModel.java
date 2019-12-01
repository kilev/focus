package ru.cft.focusstart.model.manager.GameState;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observered;
import ru.cft.focusstart.dto.GameStateChangeDTO;
import ru.cft.focusstart.timer.ITimer;

@Getter
@RequiredArgsConstructor
public class GameStateModel implements IGameState, Observered<GameStateChangeDTO> {

    private final IObserverManager observerManager;
    private final ITimer timer;

    private GameStateType gameStateType;

    public void setGameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
        sendDto();
    }

    @Override
    public void sendDto() {
//        observerManager.notifyObservers(new GameStateChangeDTO(gameStateType, timer.getTime()));
        observerManager.notifyObservers(new GameStateChangeDTO(gameStateType, 0));
    }
}
