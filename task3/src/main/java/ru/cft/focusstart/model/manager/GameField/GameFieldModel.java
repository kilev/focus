package ru.cft.focusstart.model.manager.GameField;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.model.manager.BombCount.IBombCounter;
import ru.cft.focusstart.model.manager.GameField.cell.CellModel;
import ru.cft.focusstart.model.manager.GameField.cell.CellType;
import ru.cft.focusstart.model.manager.GameState.GameStateType;
import ru.cft.focusstart.model.manager.GameState.IGameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class GameFieldModel implements IGameField {

    private final IObserverManager observerManager;

    private final IGameState gameStateModel;
    private final IBombCounter bombCounterModel;

    private CellModel[][] gameField;
    private Boolean bombGenerated;

    public void newGameField(Integer sizeX, Integer sizeY) {
        if (gameField != null && sizeX == gameField.length && sizeY == gameField[0].length) {
            setCellsClosed();
        } else {
            gameField = new CellModel[sizeX][sizeY];
            generateCells();
        }
        bombGenerated = Boolean.FALSE;
    }

    private void setCellsClosed() {
        Arrays.stream(gameField).flatMap(Arrays::stream).forEach(cellModel -> {
            cellModel.setCellType(CellType.CLOSED);
            cellModel.sendDto();
        });
    }

    private void generateCells() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = new CellModel(observerManager, gameStateModel, bombCounterModel, CellType.CLOSED, null, null, i, j);
                gameField[i][j].sendDto();
            }
        }
    }

    private void generateBombs(CellModel ignoredCell) {
        List<Boolean> bombPositions = new ArrayList<>();

        for (int i = 0; i < bombCounterModel.getTotalBombCount(); i++) {
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

    private void calculateBombArroundParameters() {
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
        if (!bombGenerated) {
            generateBombs(gameField[x][y]);
            calculateBombArroundParameters();
            gameStateModel.setGameState(GameStateType.RUN);
        }
        gameField[x][y].open();
        checkCellToOpenNeighbors(x, y);
        checkGameToWin();
    }

    private void checkCellToOpenNeighbors(Integer x, Integer y) {
        if (gameField[x][y].getBombAround() == 0) {
            getCellNeighbors(gameField[x][y]).stream().filter(cell -> cell.getCellType() == CellType.CLOSED).forEach(cell -> openCell(cell.getX(), cell.getY()));
        }
    }

    private void checkGameToWin() {
//        bombCounterModel.getTotalBombCount() == Arrays.stream(gameField).flatMap(Arrays::stream).filter(cellModel -> cellModel.getBomb() && cellModel.getCellType() == CellType.FLAGED).count()
//                &&K
        if (Arrays.stream(gameField)
                .flatMap(Arrays::stream)
                .filter(cellModel -> !cellModel.getBomb())
                .filter(cellModel -> cellModel.getCellType() == CellType.OPENED || cellModel.getCellType() == CellType.FLAGED)
                .count() == 0) {
            gameStateModel.setGameState(GameStateType.WIN);
        }
    }

    @Override
    public void flagCell(Integer x, Integer y) {
        gameField[x][y].flag();
    }
}
