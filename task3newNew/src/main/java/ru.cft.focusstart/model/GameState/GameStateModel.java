package ru.cft.focusstart.model.GameState;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.dto.GameStateChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observered;
import ru.cft.focusstart.timer.ITimer;

@Getter
@RequiredArgsConstructor
public class GameStateModel implements Observered<GameStateChangeDto> {

    private final IObserverManager observerManager;
    private final ITimer timer;

    @Setter
    private Difficulty difficulty;
    private GameStateType gameStateType;

    public void setGameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
        sendDto();
    }


    public void sendDto() {
        observerManager.notifyObservers(new GameStateChangeDto(gameStateType, difficulty, timer.getTime()));

    }
}
