package ru.cft.focusstart.view.listener;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.view.content.gameField.ViewCell;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public class ClickListener extends MouseAdapter {

    private final ModelController modelController;

    @Override
    public void mouseClicked(MouseEvent e) {
        ViewCell clickedCell = (ViewCell) e.getSource();
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                modelController.openCell(clickedCell.getCoordX(), clickedCell.getCoordY());
                break;
            case MouseEvent.BUTTON2:
                modelController.openCellNeighbors(clickedCell.getCoordX(), clickedCell.getCoordY());
                break;
            case MouseEvent.BUTTON3:
                modelController.closeCell(clickedCell.getCoordX(), clickedCell.getCoordY());
                break;
        }
    }

}
