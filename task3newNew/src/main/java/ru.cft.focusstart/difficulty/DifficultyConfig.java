package ru.cft.focusstart.difficulty;

import lombok.Getter;

@Getter
public class DifficultyConfig {

    private final Integer sizeX;
    private final Integer sizeY;
    private final Integer bombCount;

    public DifficultyConfig(Integer sizeX, Integer sizeY, Integer bombCount) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.bombCount = bombCount;
    }
}
