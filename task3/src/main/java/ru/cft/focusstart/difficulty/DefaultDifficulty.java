package ru.cft.focusstart.difficulty;

import lombok.AccessLevel;
import lombok.Getter;

public enum DefaultDifficulty {
    EAZY(9, 9, 10),
    MEDIUM(16, 16, 40),
    HARD(16, 30, 99);

    @Getter(AccessLevel.PACKAGE)
    private final DifficultyConfig difficultyConfig;

    DefaultDifficulty(int sizeX, int sizeY, int bombCount) {
        difficultyConfig = new DifficultyConfig(sizeX, sizeY, bombCount);
    }
}
