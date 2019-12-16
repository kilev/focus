package ru.cft.focusstart.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeChangeEvent extends Event {

    private final Integer currentTime;
}
