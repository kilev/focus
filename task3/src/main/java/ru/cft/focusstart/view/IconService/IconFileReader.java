package ru.cft.focusstart.view.IconService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class IconFileReader implements IconProvider {

    private static final String EMPTY_TILE_FILE_PATH = "icon/tile.png";
    private static final String TILE_WITH_FLAG_FILE_PATH = "icon/flagged.png";
    private static final String BOMB_FILE_PATH = "icon/bomb.png";

    private static final String EMPTY_FILE_PATH = "icon/";
    private static final String ICON_FORMAT = ".png";

    @Override
    public Image getEmptyTile() {
        return readIcon(EMPTY_TILE_FILE_PATH);
    }

    @Override
    public Image getTileWithNumber(int number) {
        return readIcon(EMPTY_FILE_PATH + number + ICON_FORMAT);
    }

    @Override
    public Image getTileWithFlag() {
        return readIcon(TILE_WITH_FLAG_FILE_PATH);
    }

    @Override
    public Image getBomb() {
        return readIcon(BOMB_FILE_PATH);
    }

    private Image readIcon(String path) {
        try {
            Image image = ImageIO.read(getClass().getClassLoader().getResource(path));
            return image;
        } catch (IOException | IllegalArgumentException e) {
            throw new IconReadException("Не удалось прочитать иконку: " + path, e);
        }
    }
}
