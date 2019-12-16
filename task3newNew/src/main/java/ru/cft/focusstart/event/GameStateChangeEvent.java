package ru.cft.focusstart.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.model.gamestate.GameStateType;

@Getter
@RequiredArgsConstructor
public class GameStateChangeEvent extends Event {

    private final GameStateType gameState;
    private final Difficulty difficulty;
}
