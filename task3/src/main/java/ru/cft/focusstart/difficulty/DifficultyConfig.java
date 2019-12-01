package ru.cft.focusstart.difficulty;

import lombok.Getter;

@Getter
public class DifficultyConfig {

    private final int sizeX;
    private final int sizeY;
    private final int bombCount;

    DifficultyConfig(int sizeX, int sizeY, int bombCount) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.bombCount = bombCount;
    }
}
