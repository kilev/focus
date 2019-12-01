package ru.cft.focusstart.model;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.model.manager.BombCount.BombCounterModel;
import ru.cft.focusstart.model.manager.BombCount.IBombCounter;
import ru.cft.focusstart.model.manager.GameField.GameFieldModel;
import ru.cft.focusstart.model.manager.GameField.IGameField;
import ru.cft.focusstart.model.manager.GameState.GameStateModel;
import ru.cft.focusstart.model.manager.GameState.GameStateType;
import ru.cft.focusstart.model.manager.GameState.IGameState;
import ru.cft.focusstart.timer.ITimer;

public class Model implements IModel {

    private final IGameState gameStateModel;
    private final IBombCounter bombCounterModel;
    private final IGameField gameFieldModel;

    public Model(IObserverManager observerManager, ITimer timer) {
        gameStateModel = new GameStateModel(observerManager, timer);
        bombCounterModel = new BombCounterModel(observerManager);
        gameFieldModel = new GameFieldModel(observerManager, gameStateModel, bombCounterModel);
    }

    @Override
    public void openCell(Integer x, Integer y) {
        gameFieldModel.openCell(x, y);
    }

    @Override
    public void flagCell(Integer x, Integer y) {
        gameFieldModel.flagCell(x, y);
    }

    @Override
    public void newGame(DifficultyConfig difficultyConfig) {
        gameStateModel.setGameState(GameStateType.READY_TO_RUN);
        bombCounterModel.setBombCount(difficultyConfig.getBombCount());
        gameFieldModel.newGameField(difficultyConfig.getSizeX(), difficultyConfig.getSizeY());
    }
}
