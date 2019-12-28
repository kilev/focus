package ru.cft.focusstart.model.service;

import ru.cft.focusstart.difficulty.Difficulty;

public interface IModelService {

    void openCell(Integer x, Integer y);

    void flagCell(Integer x, Integer y);

    void openCellNeighbors(Integer x, Integer y);

    void newGame(Difficulty difficulty);
}
