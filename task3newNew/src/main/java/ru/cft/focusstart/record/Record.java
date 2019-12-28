package ru.cft.focusstart.record;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.difficulty.Difficulty;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class Record implements Serializable {

    private final String playerName;
    private final Difficulty difficulty;
    private final Integer score;

}
