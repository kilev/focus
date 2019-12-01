package ru.cft.focusstart.view.iconService.iconV1;

import ru.cft.focusstart.view.iconService.SunType;

import javax.swing.*;

public interface ImageIconProvider {
    ImageIcon getEmptyTile();

    ImageIcon getTileWithNumber(int number);

    ImageIcon getTileWithFlag();

    ImageIcon getBomb();

    ImageIcon getSun(SunType sunType);

    ImageIcon getTime();

}
