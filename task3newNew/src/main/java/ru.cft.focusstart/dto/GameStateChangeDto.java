package ru.cft.focusstart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.model.GameState.GameStateType;

@Getter
@RequiredArgsConstructor
public class GameStateChangeDto extends EventDto {

    private final GameStateType gameState;
    private final Difficulty difficulty;
    private final Integer time;
}
