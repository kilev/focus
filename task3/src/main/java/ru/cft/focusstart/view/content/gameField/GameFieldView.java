package ru.cft.focusstart.view.content.gameField;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observer;
import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.dto.GameStateChangeDTO;
import ru.cft.focusstart.view.listener.ClickListener;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameFieldView extends JPanel implements Observer<GameStateChangeDTO> {

    private static final Integer CELL_PREF_SIZE = 30;

    private final IObserverManager observerManager;
    private final DifficultyController difficultyController;
    private final ClickListener cellClickListener;

    private ViewCell[][] cells;

    public GameFieldView(IObserverManager observerManager, DifficultyController difficultyController, ClickListener cellClickListener) {
        this.observerManager = observerManager;
        this.difficultyController = difficultyController;
        this.cellClickListener = cellClickListener;

        observerManager.addObserver(GameStateChangeDTO.class, this);
    }

    public void loadCells() {
        DifficultyConfig difficultyConfig = difficultyController.getCurrentDifficulty();
        if (cells != null && cells.length == difficultyConfig.getSizeX() && cells[0].length == difficultyConfig.getSizeY()) {
            return;
        }
        removeAll();
        this.setLayout(new GridLayout(difficultyConfig.getSizeX(), difficultyConfig.getSizeY()));
        cells = new ViewCell[difficultyConfig.getSizeX()][difficultyConfig.getSizeY()];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                ViewCell cell = new ViewCell(observerManager, i, j);
                cell.setPreferredSize(new Dimension(CELL_PREF_SIZE, CELL_PREF_SIZE));
                cell.addMouseListener(cellClickListener);
                cells[i][j] = cell;
                this.add(cell);
            }
        }
    }

    @Override
    public void handleEvent(GameStateChangeDTO dto) {
        switch (dto.getGameState()) {
            case WIN:
            case LOSE:
                setCellsEnabled(false);
                break;
            case READY_TO_RUN:
                setCellsEnabled(true);
                break;
        }
    }

    private void setCellsEnabled(boolean enabled) {
        Arrays.stream(cells).forEach(cell -> Arrays.stream(cell).forEach(value -> value.setEnabled(enabled)));
    }
}
