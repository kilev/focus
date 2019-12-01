package ru.cft.focusstart.view.iconService;

import javax.swing.*;
import java.awt.*;

public class ImageIconCustomer {

    private ImageIconCustomer() {
    }

    public static ImageIcon resize(ImageIcon icon, Dimension dimension) {
        return new ImageIcon(icon.getImage().getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH));
    }
}
