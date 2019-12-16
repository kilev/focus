package ru.cft.focusstart.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.model.cell.CellType;

@Getter
@RequiredArgsConstructor
public class CellChangeEvent extends Event {

    private final CellType cellType;
    private final Integer x;
    private final Integer y;
    private final Integer bombsAround;

}
