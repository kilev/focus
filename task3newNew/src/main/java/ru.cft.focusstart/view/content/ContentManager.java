package ru.cft.focusstart.view.content;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.view.content.gameField.GameFieldView;
import ru.cft.focusstart.view.content.label.BombLabel;
import ru.cft.focusstart.view.content.label.GameStatusLabel;
import ru.cft.focusstart.view.content.label.TimerLabel;
import ru.cft.focusstart.view.listener.ClickListener;
import ru.cft.focusstart.view.utils.ConstraintsUtils;
import ru.cft.focusstart.view.window.Window;

import javax.swing.*;
import java.awt.*;

public class ContentManager {

    private static final Color DEFAULT_COLOR = Color.gray;

    private final Window window;
    private final GameFieldView gameField;

    public ContentManager(IObserverManager observerManager, ModelController modelController, DifficultyController difficultyController, Window window) {
        this.window = window;

        JPanel content = new JPanel();
        TimerLabel timerLabel = new TimerLabel(observerManager);
        timerLabel.setHorizontalTextPosition(JLabel.LEFT);
        BombLabel bombLabel = new BombLabel(observerManager);
        GameStatusLabel gameStatusLabel = new GameStatusLabel(observerManager);
        gameField = new GameFieldView(observerManager, difficultyController, new ClickListener(modelController));

        content.setLayout(new GridBagLayout());
        content.setBackground(DEFAULT_COLOR);
        content.add(bombLabel, ConstraintsUtils.getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
        content.add(timerLabel, ConstraintsUtils.getConstraints(2, 0, 1, 1, GridBagConstraints.EAST));
        content.add(gameStatusLabel, ConstraintsUtils.getConstraints(1, 0, 1, 1, GridBagConstraints.CENTER));
        content.add(gameField, ConstraintsUtils.getConstraints(0, 1, 1, 3, GridBagConstraints.CENTER));
        window.add(content);

        reloadContent();
    }

    public void reloadContent() {
        gameField.loadCells();
        window.pack();
        window.setAtCenter();
    }

}
