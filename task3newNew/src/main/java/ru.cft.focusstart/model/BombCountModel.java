package ru.cft.focusstart.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.dto.BombCountChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observered;

@Getter
@RequiredArgsConstructor
public class BombCountModel implements Observered<BombCountChangeDto> {

    private final IObserverManager observerManager;

    private Integer totalBombCount;
    private Integer bombLeft;

    public void setBombCount(Integer count) {
        totalBombCount = count;
        bombLeft = count;
        sendDto();
    }

    public void bombFlaged() {
        bombLeft--;
        sendDto();
    }

    public void bombUnflaged() {
        bombLeft++;
        sendDto();
    }

    public void sendDto() {
        observerManager.notifyObservers(new BombCountChangeDto(bombLeft));
    }
}
