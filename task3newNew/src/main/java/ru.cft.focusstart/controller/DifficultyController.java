package ru.cft.focusstart.controller;

import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.difficulty.DifficultyConfig;
import ru.cft.focusstart.difficulty.IDifficultyManager;

@RequiredArgsConstructor
public class DifficultyController {

    private final IDifficultyManager difficultyManager;

    public void setDifficulty(Difficulty difficulty) {
        this.difficultyManager.setCurrentDifficulty(difficulty);
    }

    public void setCustomDifficultyConfig(DifficultyConfig difficultyConfig) {
        difficultyManager.setCustomDifficultyConfig(difficultyConfig);
    }

    public Difficulty getCurrentDifficulty() {
        return difficultyManager.getCurrentDifficulty();
    }
}
