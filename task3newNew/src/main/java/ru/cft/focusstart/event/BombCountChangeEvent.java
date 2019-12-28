package ru.cft.focusstart.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BombCountChangeEvent extends Event {

    private final int bombCount;
}
