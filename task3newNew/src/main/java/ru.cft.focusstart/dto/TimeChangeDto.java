package ru.cft.focusstart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeChangeDto extends EventDto {

    private final Integer currentTime;
}
