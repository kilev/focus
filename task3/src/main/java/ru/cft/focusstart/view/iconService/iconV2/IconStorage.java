package ru.cft.focusstart.view.iconService.iconV2;

import javax.imageio.ImageIO;
import javax.inject.Singleton;
import javax.swing.*;
import java.io.IOException;

@Singleton
public enum IconStorage {
    CLOSED("tile"),
    FLAGED("flagged"),
    BOMB("bomb"),
    OPENED_0("0"),
    OPENED_1("1"),
    OPENED_2("2"),
    OPENED_3("3"),
    OPENED_4("4"),
    OPENED_5("5"),
    OPENED_6("6"),
    OPENED_7("7"),
    OPENED_8("8"),
    TIME("time"),
    ;

    private static final String PACKAGE = "icon/";
    private static final String ICON_FORMAT = ".png";

    public static final String OPENED_NUMBER_PREFIX = "OPENED_";

    private final ImageIcon imageIcon;

    IconStorage(String path) {
        this.imageIcon = readIcon(path);
    }

    private ImageIcon readIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(PACKAGE + path + ICON_FORMAT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
