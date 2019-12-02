package ru.cft.focusstart.view.content.menu;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.record.IRecordProvider;
import ru.cft.focusstart.record.Record;
import ru.cft.focusstart.view.content.ContentManager;
import ru.cft.focusstart.view.window.DifficultyWindow;
import ru.cft.focusstart.view.window.MainWindow;

import javax.swing.*;

public class MenuManager {

    private final ModelController modelController;
    private final DifficultyController difficultyController;
    private final IRecordProvider recordProvider;

    private final ContentManager contentManager;
    private final MainWindow mainWindow;

    public MenuManager(ModelController modelController, DifficultyController difficultyController, IRecordProvider recordProvider, MainWindow mainWindow, ContentManager contentManager) {
        this.modelController = modelController;
        this.difficultyController = difficultyController;
        this.recordProvider = recordProvider;
        this.mainWindow = mainWindow;
        this.contentManager = contentManager;

        mainWindow.setJMenuBar(new JMenuBar());
        loadMenuGame();
        loadMenuReference();
        mainWindow.pack();
    }

    private void loadMenuGame() {
        JMenu menu = new JMenu(MenuConstant.MENU_GAME);
        addNewGameMenuItem(menu);
        addOptionsMenuItem(menu);
        addHighScoresItem(menu);
        menu.addSeparator();
        addCloseMenuItem(menu);
        mainWindow.getJMenuBar().add(menu);
    }

    private void addNewGameMenuItem(JMenu menu) {
        JMenuItem newGame = new JMenuItem(MenuConstant.NEW_GAME_ITEM);
        newGame.addActionListener(e -> {
            contentManager.reloadContent();
            modelController.newGame(difficultyController.getCurrentDifficulty());
        });
        menu.add(newGame);
    }

    private void addOptionsMenuItem(JMenu menu) {
        JMenuItem difficulty = new JMenuItem(MenuConstant.OPTIONS_ITEM);
        difficulty.addActionListener(e -> new DifficultyWindow(mainWindow, MenuConstant.DIFFICULTY_TITLE, difficultyController));
        menu.add(difficulty);
    }

    private void addHighScoresItem(JMenu menu) {
        JMenuItem highScores = new JMenuItem(MenuConstant.HIGH_SCORE_ITEM);
        highScores.addActionListener(e -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Record record : recordProvider.getRecords()) {
                if (record.getScore() == null) {
                    stringBuilder.append("- -");
                } else {
                    stringBuilder.append(record.getScore()).append(record.getPlayerName());
                }
                stringBuilder.append(record.getDifficulty()).append(System.lineSeparator());
            }
            JOptionPane.showMessageDialog(mainWindow, stringBuilder.toString(), MenuConstant.HIGH_SCORE_ITEM, JOptionPane.INFORMATION_MESSAGE);
        });
        menu.add(highScores);
    }

    private void loadMenuReference() {
        JMenu menu = new JMenu(MenuConstant.MENU_REFERENCE);
        JMenuItem reference = new JMenuItem(MenuConstant.HOW_TO_PLAY_ITEM);
        reference.addActionListener(e -> JOptionPane.showMessageDialog(mainWindow, MenuConstant.HOW_TO_PLAY_TEXT, MenuConstant.REGULATIONS_TITLE, JOptionPane.INFORMATION_MESSAGE));
        menu.add(reference);
        mainWindow.getJMenuBar().add(menu);
    }

    private void addCloseMenuItem(JMenu menu) {
        JMenuItem closeItem = new JMenuItem(MenuConstant.EXIT_GAME);
        closeItem.addActionListener(e -> System.exit(0));
        menu.add(closeItem);
    }
}
