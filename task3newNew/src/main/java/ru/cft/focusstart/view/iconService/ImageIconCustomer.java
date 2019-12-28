package ru.cft.focusstart.view.iconService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageIconCustomer {

    public static ImageIcon resize(ImageIcon icon, Dimension dimension) {
        return new ImageIcon(icon.getImage().getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH));
    }
}
