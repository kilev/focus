package ru.cft.focusstart.difficulty;

public interface IDifficulty {

    void setDifficulty(DefaultDifficulty defaultDifficulty);

    void setDifficulty(DifficultyConfig difficultyConfig);

    DifficultyConfig getDefaultDifficulty(DefaultDifficulty defaultDifficulty);

    DifficultyConfig getCurrentDifficulty();


}
