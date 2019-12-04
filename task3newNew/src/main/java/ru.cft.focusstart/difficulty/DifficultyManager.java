package ru.cft.focusstart.difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DifficultyManager implements IDifficultyManager {

    private Difficulty currentDifficulty = Difficulty.EAZY;

    @Override
    public void setCustomDifficultyConfig(DifficultyConfig difficultyConfig) {
        Difficulty.CUSTOM.setCustomDifficulty(difficultyConfig);
    }
}
