package ru.cft.focusstart.controller;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.model.service.IModelService;

@RequiredArgsConstructor
public class ModelController {

    private final IModelService modelService;

    public void openCell(Integer x, Integer y) {
        modelService.openCell(x, y);
    }

    public void openCellNeighbors(Integer x, Integer y) {
        modelService.openCellNeighbors(x, y);
    }

    public void closeCell(Integer x, Integer y) {
        modelService.flagCell(x, y);
    }

    public void newGame(Difficulty difficulty) {
        modelService.newGame(difficulty);
    }
}
