package ru.cft.focusstart.view.IconService;

import java.awt.*;

public interface IconProvider {
    Image getEmptyTile();

    Image getTileWithNumber(int number);

    Image getTileWithFlag();

    Image getBomb();

}
