package ru.cft.focusstart.dto;

import lombok.Getter;

@Getter
public class BombCountChangeDTO extends EventDto {

    private final int bombCount;

    public BombCountChangeDTO(int bombCount) {
        this.bombCount = bombCount;
    }
}
