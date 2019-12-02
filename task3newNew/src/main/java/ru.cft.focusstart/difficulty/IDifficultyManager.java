package ru.cft.focusstart.difficulty;

public interface IDifficultyManager {

    void setCurrentDifficulty(Difficulty difficulty);

    void setCustomDifficultyConfig(DifficultyConfig difficultyConfig);

//    DifficultyConfig getDifficulty(Difficulty defaultDifficulty);

    Difficulty getCurrentDifficulty();


}
