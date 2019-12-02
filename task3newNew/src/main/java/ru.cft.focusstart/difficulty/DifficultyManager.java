package ru.cft.focusstart.difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DifficultyManager implements IDifficultyManager {
//
//    private final static Integer MIN_X_SIZE = 4;
//    private final static Integer MIN_Y_SIZE = 8;
//    private final static Integer MIN_BOMB_COUNT = 4;
//
//    private final static Integer MAX_X_SIZE = 40;
//    private final static Integer MAX_Y_SIZE = 40;
//    private final static Integer MAX_BOMB_COUNT = 1000;

//    private DifficultyConfig difficultyConfig = DefaultDifficulty.EAZY.getDifficultyConfig();

    private Difficulty currentDifficulty = Difficulty.EAZY;

    @Override
    public void setCustomDifficultyConfig(DifficultyConfig difficultyConfig) {
//        this.difficultyConfig = validateConfig(difficultyConfig);
        Difficulty.CUSTOM.setCustomDifficulty(difficultyConfig);
    }
}
