package ru.cft.focusstart.view.Menu;

import javax.swing.*;

public class MenuManager {

    private final JMenuBar client;
    private boolean clientModified = false;

    public MenuManager(JMenuBar client) {
        this.client = client;
    }

    public void showMenu() {
        if (!clientModified) {
            createMenuBar();
        }
        client.setVisible(true);
    }

    public void hideMenu() {
        client.setVisible(false);
    }

    private void createMenuBar() {
        loadMenuGame();
        loadMenuReference();
        clientModified = true;
    }

    private void loadMenuGame() {
        JMenu menu = new JMenu(MenuNameConstant.MENU_GAME);
        menu.add(new JMenuItem(MenuNameConstant.NEW_GAME_ITEM));
        menu.add(new JMenuItem(MenuNameConstant.OPTIONS_ITEM));
        menu.add(new JMenuItem(MenuNameConstant.HIGH_SCORE_ITEM));
        menu.addSeparator();
        menu.add(new JMenuItem(MenuNameConstant.EXIT_GAME));
        client.add(menu);
    }

    private void loadMenuReference() {
        JMenu menu = new JMenu(MenuNameConstant.MENU_REFERENCE);
        menu.add(new JMenuItem(MenuNameConstant.HOW_TO_PLAY_ITEM));
        client.add(menu);
    }

}
