package ru.cft.focusstart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cft.focusstart.model.manager.GameField.cell.CellType;

@Getter
@AllArgsConstructor
public class CellChangeDTO extends EventDto {

    private final CellType cellType;
    private final Integer x;
    private final Integer y;
    private final Integer bombsAround;

}
