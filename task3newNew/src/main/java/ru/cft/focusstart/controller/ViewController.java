package ru.cft.focusstart.controller;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.view.IView;

@RequiredArgsConstructor
public class ViewController {

    private final IView view;

    public String getUserName() {
        return view.askUserForName();
    }
}
