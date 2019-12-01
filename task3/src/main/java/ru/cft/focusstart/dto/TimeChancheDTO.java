package ru.cft.focusstart.dto;

import lombok.Getter;

@Getter
public class TimeChancheDTO extends EventDto {

    private final int currentTime;

    public TimeChancheDTO(int currentTime) {
        this.currentTime = currentTime;
    }
}
