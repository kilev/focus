package ru.cft.focusstart.timer;

import ru.cft.focusstart.dto.GameStateChangeDto;
import ru.cft.focusstart.dto.TimeChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SmartTimer implements ITimer, Observer<GameStateChangeDto> {

    private final IObserverManager observerManager;
    private Timer timer;

    private long systemNanoTime;
    private AtomicInteger time = new AtomicInteger(0);

    public SmartTimer(IObserverManager observerManager) {
        this.observerManager = observerManager;
        observerManager.addObserver(GameStateChangeDto.class, this);
    }

    class timeTask extends TimerTask implements Runnable {
        @Override
        public void run() {
            if (System.nanoTime() - 1000000000 > systemNanoTime) {
                systemNanoTime = System.nanoTime();
                observerManager.notifyObservers(new TimeChangeDto(time.incrementAndGet()));
            }
        }
    }

    @Override
    public Integer getTime() {
        return time.get();
    }

    @Override
    public void handleEvent(GameStateChangeDto dto) {
        switch (dto.getGameState()) {
            case RUN:
                reset();
                return;
            case LOSE:
            case WIN:
                timer.cancel();
        }
    }

    private void reset() {
        time.set(0);
        systemNanoTime = System.nanoTime();
        timer = new Timer();
        timer.schedule(new timeTask(), 0, 500);
    }
}
