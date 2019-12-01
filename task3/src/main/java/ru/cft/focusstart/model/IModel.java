package ru.cft.focusstart.model;

import ru.cft.focusstart.difficulty.DifficultyConfig;

public interface IModel {

    void openCell(Integer x, Integer y);

    void flagCell(Integer x, Integer y);

    void newGame(DifficultyConfig difficultyConfig);
}
