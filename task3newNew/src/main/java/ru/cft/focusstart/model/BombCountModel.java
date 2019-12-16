package ru.cft.focusstart.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.event.BombCountChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observable;

@Getter
@RequiredArgsConstructor
public class BombCountModel implements Observable {

    private final IObserverManager observerManager;

    private Integer totalBombCount;
    private Integer bombLeft;

    public void setBombCount(Integer count) {
        totalBombCount = count;
        bombLeft = count;
        notifyObservers();
    }

    public void bombFlaged() {
        bombLeft--;
        notifyObservers();
    }

    public void bombUnflaged() {
        bombLeft++;
        notifyObservers();
    }

    public void notifyObservers() {
        observerManager.notifyObservers(new BombCountChangeEvent(bombLeft));
    }
}
