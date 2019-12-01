package ru.cft.focusstart.controller;

import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.model.IModel;

public class ModelController {

    private final IModel model;

    public ModelController(IModel model) {
        this.model = model;
    }

    public void openCell(Integer x, Integer y) {
        model.openCell(x, y);
    }

    public void closeCell(Integer x, Integer y) {
        model.flagCell(x, y);
    }

    public void newGame(DifficultyConfig difficultyConfig) {
        model.newGame(difficultyConfig);
    }
}
