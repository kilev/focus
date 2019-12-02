package ru.cft.focusstart.view.window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    Window() {
        setResizable(false);
        setVisible(true);
    }

    public void setAtCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = getPreferredSize();
        setBounds((int) (screenSize.width - windowSize.getWidth()) / 2,
                (int) (screenSize.height - windowSize.getHeight()) / 2,
                (int) windowSize.getWidth(),
                (int) windowSize.getHeight());
    }
}
