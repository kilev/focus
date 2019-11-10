package ru.cft.focusstart.view.IconService;

import java.awt.*;

public class IconScaler {

    private IconScaler() {
    }

    public static Image scaleImage(Image icon, int size) {
        return icon.getScaledInstance(size, size, Image.SCALE_SMOOTH);
    }
}
