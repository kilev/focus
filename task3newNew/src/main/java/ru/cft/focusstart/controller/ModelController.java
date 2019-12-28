package ru.cft.focusstart.controller;

import com.google.inject.Inject;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.model.service.IModelService;

public class ModelController {

    private final IModelService modelService;

    @Inject
    public ModelController(IModelService modelService) {
        this.modelService = modelService;
    }

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
