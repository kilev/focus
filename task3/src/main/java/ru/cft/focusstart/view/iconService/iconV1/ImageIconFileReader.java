package ru.cft.focusstart.view.iconService.iconV1;

import ru.cft.focusstart.view.iconService.SunType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class ImageIconFileReader implements ImageIconProvider {

    private static final String DEFAULT_PACKAGE = "icon/";
    private static final String ICON_FORMAT = ".png";

    private static final String EMPTY_TILE_FILE_PATH = DEFAULT_PACKAGE + "tile.png";
    private static final String TILE_WITH_FLAG_FILE_PATH = DEFAULT_PACKAGE + "flagged.png";
    private static final String BOMB_FILE_PATH = DEFAULT_PACKAGE + "bomb.png";
    private static final String TIME_PATH = DEFAULT_PACKAGE + "time.png";

    private static final String DEFAULT_SUN = DEFAULT_PACKAGE + "defaultSun.png";
    private static final String BAD_SUN = DEFAULT_PACKAGE + "badSun.png";
    private static final String GOOD_SUN = DEFAULT_PACKAGE + "goodSun.png";
    private static final String THUG_SUN = DEFAULT_PACKAGE + "thugSun.png";

    private static final String READ_ERROR_MESSAGE = "Не удалось прочитать иконку: ";

    @Override
    public ImageIcon getEmptyTile() {
        return readImageIcon(EMPTY_TILE_FILE_PATH);
    }

    @Override
    public ImageIcon getTileWithNumber(int number) {
        return readImageIcon(DEFAULT_PACKAGE + number + ICON_FORMAT);
    }

    @Override
    public ImageIcon getTileWithFlag() {
        return readImageIcon(TILE_WITH_FLAG_FILE_PATH);
    }

    @Override
    public ImageIcon getBomb() {
        return readImageIcon(BOMB_FILE_PATH);
    }

    @Override
    public ImageIcon getSun(SunType sunType) {
        try {
            switch (sunType) {
                case DEFAULT:
                    return readImageIcon(DEFAULT_SUN);
                case BAD:
                    return readImageIcon(BAD_SUN);
                case GOOD:
                    return readImageIcon(GOOD_SUN);
                case THUG:
                    return readImageIcon(THUG_SUN);
                default:
                    throw new UnsupportedOperationException("Не поддерживаемый тип иконки.");
            }
        } catch (UnsupportedOperationException e) {
            throw new IconReadException(READ_ERROR_MESSAGE + sunType, e);
        }
    }

    @Override
    public ImageIcon getTime() {
        return readImageIcon(TIME_PATH);
    }

    private ImageIcon readImageIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(path)));
        } catch (IOException | IllegalArgumentException e) {
            throw new IconReadException(READ_ERROR_MESSAGE + path, e);
        }
    }
}
