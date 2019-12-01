package ru.cft.focusstart.controller;

import ru.cft.focusstart.view.IView;

public class ViewController {

    private final IView view;

    public ViewController(IView view) {
        this.view = view;
    }

    public String getUserName() {
        return view.askUserForName();
    }
}
