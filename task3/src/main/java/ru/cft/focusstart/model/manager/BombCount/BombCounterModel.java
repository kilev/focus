package ru.cft.focusstart.model.manager.BombCount;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.Observered;
import ru.cft.focusstart.dto.BombCountChangeDTO;

@Getter
@RequiredArgsConstructor
public class BombCounterModel implements IBombCounter, Observered<BombCountChangeDTO> {

    private final IObserverManager observerManager;

    private Integer totalBombCount;
    private Integer bombLeft;

    @Override
    public void setBombCount(Integer count) {
        totalBombCount = count;
        bombLeft = count;
        sendDto();
    }

    @Override
    public void bombFlaged() {
        bombLeft--;
        sendDto();
    }

    @Override
    public void bombUnflaged() {
        bombLeft++;
        sendDto();
    }

    @Override
    public void sendDto() {
        observerManager.notifyObservers(new BombCountChangeDTO(bombLeft));
    }
}
