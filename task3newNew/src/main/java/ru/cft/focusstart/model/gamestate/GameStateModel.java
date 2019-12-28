package ru.cft.focusstart.model.gamestate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.event.GameStateChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observable;

@Getter
@RequiredArgsConstructor
public class GameStateModel implements Observable {

    private final IObserverManager observerManager;

    @Setter
    private Difficulty difficulty;
    private GameStateType gameStateType;

    public void setGameState(GameStateType gameStateType) {
        this.gameStateType = gameStateType;
        notifyObservers();
    }

    public void notifyObservers() {
        observerManager.notifyObservers(new GameStateChangeEvent(gameStateType, difficulty));

    }
}
