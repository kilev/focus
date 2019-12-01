package ru.cft.focusstart.view.iconService.iconV1;

import ru.cft.focusstart.view.iconService.ImageIconCustomer;
import ru.cft.focusstart.view.iconService.SunType;

import javax.swing.*;
import java.awt.*;

public class ResizedImageIconFileReader extends ImageIconFileReader implements ResizedImageIconProvider {

    public ImageIcon getEmptyTile(Dimension dimension) {
        return ImageIconCustomer.resize(getEmptyTile(), dimension);
    }

    public ImageIcon getTileWithNumber(int number, Dimension dimension) {
        return ImageIconCustomer.resize(getTileWithNumber(number), dimension);
    }

    public ImageIcon getTileWithFlag(Dimension dimension) {
        return ImageIconCustomer.resize(getTileWithFlag(), dimension);
    }

    public ImageIcon getBomb(Dimension dimension) {
        return ImageIconCustomer.resize(getBomb(), dimension);
    }

    public ImageIcon getSun(SunType sunType, Dimension dimension) {
        return ImageIconCustomer.resize(getSun(sunType), dimension);
    }

    public ImageIcon getTime(Dimension dimension) {
        return ImageIconCustomer.resize(getTime(), dimension);
    }
}
