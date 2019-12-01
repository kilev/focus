package ru.cft.focusstart.view.iconService.iconV1;

import ru.cft.focusstart.view.iconService.SunType;

import javax.swing.*;
import java.awt.*;

public interface ResizedImageIconProvider extends ImageIconProvider {

    ImageIcon getEmptyTile(Dimension dimension);

    ImageIcon getTileWithNumber(int number, Dimension dimension);

    ImageIcon getTileWithFlag(Dimension dimension);

    ImageIcon getBomb(Dimension dimension);

    ImageIcon getSun(SunType sunType, Dimension dimension);

    ImageIcon getTime(Dimension dimension);
}
