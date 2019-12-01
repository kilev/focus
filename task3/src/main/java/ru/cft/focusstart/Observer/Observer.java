package ru.cft.focusstart.Observer;

import ru.cft.focusstart.dto.EventDto;

public interface Observer<T extends EventDto> {

    void handleEvent(T dto);

    default void handleDto(EventDto dto) {
        handleEvent((T) dto);
    }
}
