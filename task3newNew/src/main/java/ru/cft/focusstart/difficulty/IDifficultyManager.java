package ru.cft.focusstart.difficulty;

public interface IDifficultyManager {

    void setCurrentDifficulty(Difficulty difficulty);

    void setCustomDifficultyConfig(DifficultyConfig difficultyConfig);

    Difficulty getCurrentDifficulty();


}
