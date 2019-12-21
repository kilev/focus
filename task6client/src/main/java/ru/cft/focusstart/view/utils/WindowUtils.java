package ru.cft.focusstart.view.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WindowUtils {

    public static JFrame getMainWindow() {
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        return mainWindow;
    }

    public static void setAtCenter(JFrame window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getPreferredSize();
        window.setBounds((int) (screenSize.width - windowSize.getWidth()) / 2,
                (int) (screenSize.height - windowSize.getHeight()) / 2,
                (int) windowSize.getWidth(),
                (int) windowSize.getHeight());
    }
}
