package ru.cft.focusstart;

import ru.cft.focusstart.views.MineSweeperGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MineSweeperGUI gui = new MineSweeperGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
