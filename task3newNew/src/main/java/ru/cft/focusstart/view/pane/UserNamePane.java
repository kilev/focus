package ru.cft.focusstart.view.pane;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserNamePane extends JOptionPane {

    private static final String TITLE = "input your name:";
    private static final String UNKNOW_USER = "Unknown";

    static public String askUser() {
        String userName = showInputDialog(TITLE);
        return Objects.requireNonNullElse(userName, "Unknown");
    }
}
