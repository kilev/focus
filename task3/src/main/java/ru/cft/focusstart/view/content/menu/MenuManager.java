package ru.cft.focusstart.view.content.menu;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.view.content.ContentManager;
import ru.cft.focusstart.view.window.MainWindow;

import javax.swing.*;

public class MenuManager {

    private final ModelController modelController;
    private final DifficultyController difficultyController;

    private final ContentManager contentManager;
    private final MainWindow mainWindow;

    public MenuManager(ModelController modelController, DifficultyController difficultyController, MainWindow mainWindow, ContentManager contentManager) {
        this.modelController = modelController;
        this.difficultyController = difficultyController;
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

        menu.add(getOptionsSubMenu());
        menu.add(new JMenuItem(MenuConstant.HIGH_SCORE_ITEM));
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

    private void addCloseMenuItem(JMenu menu) {
        JMenuItem closeItem = new JMenuItem(MenuConstant.EXIT_GAME);
        closeItem.addActionListener(e -> System.exit(0));
        menu.add(closeItem);
    }

    private JMenu getOptionsSubMenu() {
        JMenu options = new JMenu(MenuConstant.OPTIONS_ITEM);
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem eazyDifficulty = new JRadioButtonMenuItem(MenuConstant.DIFFICULTY_EASY);
        eazyDifficulty.setSelected(true);
        group.add(eazyDifficulty);
        options.add(eazyDifficulty);

        JRadioButtonMenuItem mediumDifficulty = new JRadioButtonMenuItem(MenuConstant.DIFFICULTY_MEDIUM);
        group.add(mediumDifficulty);
        options.add(mediumDifficulty);

        JRadioButtonMenuItem hardDifficulty = new JRadioButtonMenuItem(MenuConstant.DIFFICULTY_HARD);
        group.add(hardDifficulty);
        options.add(hardDifficulty);

        JRadioButtonMenuItem customDifficulty = new JRadioButtonMenuItem(MenuConstant.DIFFICULTY_CUSTOM);
        group.add(customDifficulty);
        options.add(customDifficulty);

        return options;
    }

    private void loadMenuReference() {
//        JMenu menu = new JMenu(MenuConstant.MENU_REFERENCE);
//        JMenuItem reference = new JMenuItem(MenuConstant.HOW_TO_PLAY_ITEM);
//        reference.addActionListener(e -> {
//            new JOptionPane()
//        });
//        menu.add();
//        mainWindow.getJMenuBar().add(menu);
    }
}
