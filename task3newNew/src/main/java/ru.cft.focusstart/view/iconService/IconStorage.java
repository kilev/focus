package ru.cft.focusstart.view.iconService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

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

    public static final String OPENED_NUMBER_PREFIX = "OPENED_";

    private static final Logger logger = LoggerFactory.getLogger(IconStorage.class);

    private static final String READ_ICON_ERROR_TEXT = "Ощибка чтения иконок";

    private static final String PACKAGE = "icon/";
    private static final String ICON_FORMAT = ".png";

    private final ImageIcon imageIcon;

    IconStorage(String path) {
        this.imageIcon = readIcon(path);
    }

    private ImageIcon readIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(PACKAGE + path + ICON_FORMAT)));
        } catch (IOException e) {
            logger.error(READ_ICON_ERROR_TEXT, e);
        }
        return null;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
