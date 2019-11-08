package ru.cft.focusstart.views;

import javax.swing.*;
import java.awt.*;

public class MineSweeperGUI extends JFrame {

    private static final String TITLE = "Mine Sweeper";
    private static final int GUIWIDTH = 300;
    private static final int GUIHEIGHT = 350;

    private final JPanel mainPanel = new JPanel();
    private final JPopupMenu menu = new JPopupMenu();
    private GameField gameField = new GameField();

    public MineSweeperGUI() {
        this.setTitle(TITLE);
        this.setResizable(false);
        setDefoultBounds();

        this.add(mainPanel);

        menu.add(new JMenuItem());


        mainPanel.add(menu);
    }

    private void setDefoultBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - GUIWIDTH) / 2, (dimension.height - GUIHEIGHT) / 2, GUIWIDTH, GUIHEIGHT);
    }

}
