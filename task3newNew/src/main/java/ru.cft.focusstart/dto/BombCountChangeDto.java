package ru.cft.focusstart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BombCountChangeDto extends EventDto {

    private final int bombCount;
}
