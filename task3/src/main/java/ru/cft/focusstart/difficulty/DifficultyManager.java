package ru.cft.focusstart.difficulty;

public class DifficultyManager implements IDifficulty {

    private DifficultyConfig difficultyConfig = DefaultDifficulty.EAZY.getDifficultyConfig();

    @Override
    public void setDifficulty(DefaultDifficulty defaultDifficulty) {
        difficultyConfig = defaultDifficulty.getDifficultyConfig();
    }

    @Override
    public void setDifficulty(DifficultyConfig difficultyConfig) {
        this.difficultyConfig = difficultyConfig;
    }

    @Override
    public DifficultyConfig getDefaultDifficulty(DefaultDifficulty defaultDifficulty) {
        return defaultDifficulty.getDifficultyConfig();
    }

    @Override
    public DifficultyConfig getCurrentDifficulty() {
        return difficultyConfig;
    }
}
