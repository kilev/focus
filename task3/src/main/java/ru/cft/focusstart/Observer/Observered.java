package ru.cft.focusstart.Observer;

import ru.cft.focusstart.dto.EventDto;

public interface Observered<T extends EventDto> {

    void sendDto();

//    GlobalGateWay getGlobalGateWay();
//
//    default void sendNotify(T dto) {
//        getGlobalGateWay().notifyObservers(dto);
//    }
}
