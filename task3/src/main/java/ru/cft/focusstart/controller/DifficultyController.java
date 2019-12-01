package ru.cft.focusstart.controller;

import ru.cft.focusstart.difficulty.DefaultDifficulty;
import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.difficulty.IDifficulty;

import javax.inject.Singleton;

@Singleton
public class DifficultyController {

    private final IDifficulty difficulty;

    public DifficultyController(IDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setDifficulty(DefaultDifficulty defaultDifficulty) {
        difficulty.setDifficulty(defaultDifficulty);
    }

    public void setDifficulty(DifficultyConfig difficultyConfig) {
        difficulty.setDifficulty(difficultyConfig);
    }

    public DifficultyConfig getDefaultDifficulty(DefaultDifficulty defaultDifficulty) {
        return difficulty.getDefaultDifficulty(defaultDifficulty);
    }

    public DifficultyConfig getCurrentDifficulty() {
        return difficulty.getCurrentDifficulty();
    }

}
