package ru.cft.focusstart.view.content;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.view.content.gameField.GameFieldView;
import ru.cft.focusstart.view.content.label.BombLabel;
import ru.cft.focusstart.view.content.label.TimerLabel;
import ru.cft.focusstart.view.listener.ClickListener;
import ru.cft.focusstart.view.window.Window;

import javax.swing.*;
import java.awt.*;

public class ContentManager {

    private static final Color DEFAULT_COLOR = Color.gray;

    private final Window window;

    private final JPanel content;
    private final TimerLabel timerLabel;
    private final BombLabel bombLabel;
    private final GameFieldView gameField;

    public ContentManager(IObserverManager observerManager, ModelController modelController, DifficultyController difficultyController, Window window) {
        this.window = window;

        content = new JPanel();
        timerLabel = new TimerLabel(observerManager);
        bombLabel = new BombLabel(observerManager);
        gameField = new GameFieldView(observerManager, difficultyController, new ClickListener(modelController));

        content.setLayout(new GridBagLayout());
        content.setBackground(DEFAULT_COLOR);
        loadLabelBomb();
        loadLabelTime();
        loadGameField();
        window.add(content);

        reloadContent();
    }

    public void reloadContent() {
        gameField.loadCells();
        window.pack();
        window.setAtCenter();
    }

    private void loadLabelBomb() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        content.add(bombLabel, constraints);
//        content.add(new JLabel("azazaza"), constraints);
    }

    private void loadLabelTime() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        timerLabel.setHorizontalTextPosition(JLabel.LEFT);
        content.add(timerLabel, constraints);
    }

    private void loadGameField() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        content.add(gameField, constraints);
    }
}
