package ru.cft.focusstart.model.service;

import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.model.BombCountModel;
import ru.cft.focusstart.model.GameState.GameStateModel;
import ru.cft.focusstart.model.GameState.GameStateType;
import ru.cft.focusstart.model.cell.CellModel;
import ru.cft.focusstart.model.cell.CellType;
import ru.cft.focusstart.observer.IObserverManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModelService implements IModelService {

    private final IObserverManager observerManager;

    private final GameStateModel gameStateModel;
    private final BombCountModel bombCountModel;
    private CellModel[][] gameField;

    private Boolean bombGenerated;

    public ModelService(IObserverManager observerManager) {
        this.observerManager = observerManager;
        bombCountModel = new BombCountModel(observerManager);
        gameStateModel = new GameStateModel(observerManager);
    }

    @Override
    public void newGame(Difficulty difficulty) {
        gameStateModel.setGameState(GameStateType.READY_TO_RUN);
        gameStateModel.setDifficulty(difficulty);
        DifficultyConfig difficultyConfig = difficulty.getDifficultyConfig();
        bombCountModel.setBombCount(difficultyConfig.getBombCount());
        newGameField(difficultyConfig.getSizeX(), difficultyConfig.getSizeY());
    }

    private void newGameField(Integer sizeX, Integer sizeY) {
        gameField = new CellModel[sizeX][sizeY];
        generateCells();
        bombGenerated = Boolean.FALSE;
    }

    private void generateCells() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = new CellModel(observerManager, CellType.CLOSED, null, null, i, j);
                gameField[i][j].sendDto();
            }
        }
    }

    private void generateBombs(CellModel ignoredCell) {
        List<Boolean> bombPositions = new ArrayList<>();

        for (int i = 0; i < bombCountModel.getTotalBombCount(); i++) {
            bombPositions.add(Boolean.TRUE);
        }
        for (int i = bombPositions.size(); i < gameField.length * gameField[0].length - 1; i++) {
            bombPositions.add(Boolean.FALSE);
        }
        Collections.shuffle(bombPositions);
        bombPositions.add(ignoredCell.getX() * gameField[0].length + ignoredCell.getY(), Boolean.FALSE);
        setBombs(bombPositions);
        bombGenerated = Boolean.TRUE;
    }

    private void setBombs(List<Boolean> bombPositions) {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j].setBomb(bombPositions.get(i * gameField.length + j));
            }
        }
    }

    private void calculateBombArround() {
        Arrays.stream(gameField).flatMap(Arrays::stream)
                .forEach(cellModel -> cellModel.setBombAround((int) getCellNeighbors(cellModel).stream().filter(CellModel::getBomb).count()));
    }

    private List<CellModel> getCellNeighbors(CellModel cell) {
        List<CellModel> neighbors = new ArrayList<>();
        for (int i = cell.getX() - 1; i <= cell.getX() + 1; i++) {
            for (int j = cell.getY() - 1; j <= cell.getY() + 1; j++) {
                if ((i >= 0 && i < gameField.length) && (j >= 0 && j < gameField[i].length) && (i != cell.getX() || j != cell.getY())) {
                    neighbors.add(gameField[i][j]);
                }
            }
        }
        return neighbors;
    }

    @Override
    public void openCell(Integer x, Integer y) {
        openCell(gameField[x][y]);
    }

    private void openCell(CellModel cell) {
        if (!bombGenerated) {
            generateBombs(cell);
            calculateBombArround();
            gameStateModel.setGameState(GameStateType.RUN);
        }
        cell.open();
        if (cell.getCellType() == CellType.EXPLODED) {
            gameStateModel.setGameState(GameStateType.LOSE);
            openLostBombs();
            return;
        }
        if (gameIsWin()) {
            gameStateModel.setGameState(GameStateType.WIN);
            return;
        }
        checkToOpenNeighbors(cell);
    }

    private void checkToOpenNeighbors(CellModel applicant) {
        if (applicant.getBombAround() == 0 && !applicant.getBomb()) {
            getCellNeighbors(applicant).stream()
                    .filter(cell -> cell.getCellType() == CellType.CLOSED)
                    .forEach(this::openCell);
        }
    }

    private Boolean gameIsWin() {
        return (Arrays.stream(gameField)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.getBomb())
                .noneMatch(cell -> cell.getCellType() != CellType.OPENED));
    }

    @Override
    public void flagCell(Integer x, Integer y) {
        flagCell(gameField[x][y]);
    }

    private void flagCell(CellModel cell) {
        if (cell.getCellType() == CellType.CLOSED || cell.getCellType() == CellType.FLAGGED) {
            cell.flag();
            if (cell.getCellType() == CellType.FLAGGED) {
                bombCountModel.bombFlaged();
            } else if (cell.getCellType() == CellType.CLOSED) {
                bombCountModel.bombUnflaged();
            }
        }
    }

    @Override
    public void openCellNeighbors(Integer x, Integer y) {
        openCellNeighbors(gameField[x][y]);
    }

    private void openCellNeighbors(CellModel cell) {
        if (cell.getCellType() == CellType.OPENED) {
            List<CellModel> neighbors = getCellNeighbors(cell);
            if (neighbors.stream().filter(neighbor -> neighbor.getCellType() == CellType.FLAGGED).count() == cell.getBombAround()) {
                neighbors.forEach(this::openCell);
            }
        }
    }

    private void openLostBombs() {
        Arrays.stream(gameField)
                .flatMap(Arrays::stream)
                .filter(CellModel::getBomb)
                .filter(cell -> cell.getCellType() == CellType.CLOSED)
                .forEach(CellModel::open);
    }

}
