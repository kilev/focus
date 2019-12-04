package ru.cft.focusstart.difficulty;

import lombok.Getter;

public enum Difficulty {
    EAZY(9, 9, 10),
    MEDIUM(16, 16, 40),
    HARD(16, 30, 99),
    CUSTOM(40, 40, 99),
    ;

    private final static int MIN_X_SIZE = 4;
    private final static int MIN_Y_SIZE = 8;
    private final static int MIN_BOMB_COUNT = 4;

    private final static int MAX_X_SIZE = 40;
    private final static int MAX_Y_SIZE = 40;
    private final static int MAX_BOMB_COUNT = 1000;

    @Getter
    private DifficultyConfig difficultyConfig;

    Difficulty(int sizeX, int sizeY, int bombCount) {
        difficultyConfig = new DifficultyConfig(sizeX, sizeY, bombCount);
    }

    public void setCustomDifficulty(DifficultyConfig difficultyConfig) {
        if (this == Difficulty.CUSTOM) {
            this.difficultyConfig = validateConfig(difficultyConfig);
        }
    }

    private DifficultyConfig validateConfig(DifficultyConfig difficultyConfig) {
        Integer newSizeX = difficultyConfig.getSizeX();
        Integer newSizeY = difficultyConfig.getSizeY();
        Integer newBomb = difficultyConfig.getBombCount();

        if (newSizeX > MAX_X_SIZE) {
            newSizeX = MAX_X_SIZE;
        } else if (newSizeX < MIN_X_SIZE) {
            newSizeX = MIN_X_SIZE;
        }
        if (newSizeY > MAX_Y_SIZE) {
            newSizeY = MAX_Y_SIZE;
        } else if (newSizeY < MIN_Y_SIZE) {
            newSizeY = MIN_Y_SIZE;
        }
        if (newBomb > MAX_BOMB_COUNT) {
            newBomb = MAX_BOMB_COUNT;
        } else if (newBomb < MIN_BOMB_COUNT) {
            newBomb = MIN_BOMB_COUNT;
        }
        return new DifficultyConfig(newSizeX, newSizeY, newBomb);
    }

}
