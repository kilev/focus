package ru.cft.focusstart.difficulty;

import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Singleton
public class DifficultyManager implements IDifficultyManager {

    private Difficulty currentDifficulty = Difficulty.EAZY;

    @Override
    public void setCustomDifficultyConfig(DifficultyConfig difficultyConfig) {
        Difficulty.CUSTOM.setCustomDifficulty(difficultyConfig);
    }
}
