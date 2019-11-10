package ru.cft.focusstart.view.GameField;

import ru.cft.focusstart.view.IconService.IconFileReader;
import ru.cft.focusstart.view.IconService.IconProvider;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {

    private final IconProvider icons = new IconFileReader();

    private final int BATTLE_FIELD_SIZE;
//    private final int CELL_SIZE;

    public GameField(int size) {
        BATTLE_FIELD_SIZE = size;
//        CELL_SIZE = calculateCellSize();
        loadCells(BATTLE_FIELD_SIZE);
        this.setLayout(new GridLayout(size, size, 0, 0));
    }

//    private int calculateCellSize(){
//        return ;
//    }

    private void loadCells(int count) {
        ImageIcon icon = new ImageIcon(icons.getEmptyTile());
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                JButton button = new JButton();
                button.setIcon(icon);
                this.add(button);
            }
        }
    }

}
