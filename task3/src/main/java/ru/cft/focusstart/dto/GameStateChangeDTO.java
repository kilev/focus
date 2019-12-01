package ru.cft.focusstart.dto;

import lombok.Getter;
import ru.cft.focusstart.model.manager.GameState.GameStateType;

@Getter
public class GameStateChangeDTO extends EventDto {

    private final GameStateType gameState;
    private final Integer time;

    public GameStateChangeDTO(GameStateType gameState, Integer time) {
        this.gameState = gameState;
        this.time = time;
    }
}
