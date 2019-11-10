package ru.cft.focusstart.view;


import ru.cft.focusstart.view.GameField.GameField;
import ru.cft.focusstart.view.Menu.MenuManager;

import javax.swing.*;
import java.awt.*;

public class MinesweeperGUI extends JFrame {

    private static final String TITLE = "Сапер";
    private static final int GUIWIDTH = 300;
    private static final int GUIHEIGHT = 350;
    private static final int BATTLE_FIELD_DEFAULT_SIZE = 9;

    private final JPanel mainPanel = new JPanel();

    private final JMenuBar menu = new JMenuBar();
    private final MenuManager menuManager = new MenuManager(menu);

    private GameField gameField = new GameField(BATTLE_FIELD_DEFAULT_SIZE);

    public MinesweeperGUI() {
        this.setJMenuBar(menu);

        setDefaultFrameConfiguration();
        menuManager.showMenu();

        this.add(mainPanel);

        getContentPane().add(gameField);
        this.setVisible(true);
    }

    private void setDefaultFrameConfiguration() {
        this.setTitle(TITLE);
//        this.setResizable(false);
        setDefaultBounds();
    }

    private void setDefaultBounds() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((dimension.width - GUIWIDTH) / 2, (dimension.height - GUIHEIGHT) / 2, GUIWIDTH, GUIHEIGHT);
    }

}
