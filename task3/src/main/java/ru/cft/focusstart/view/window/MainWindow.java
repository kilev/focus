package ru.cft.focusstart.view.window;

import ru.cft.focusstart.view.iconService.iconV2.IconStorage;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends Window {

    private static final String NAME = "Сапер";
    private static final Image IMAGE = IconStorage.BOMB.getImageIcon().getImage();

    public MainWindow() {
        setTitle(NAME);
        setIconImage(IMAGE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
