package ru.cft.focusstart.timer;

import ru.cft.focusstart.dto.GameStateChangeDto;
import ru.cft.focusstart.dto.TimeChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SmartTimer implements ITimer, Observer<GameStateChangeDto> {

    private final IObserverManager observerManager;

    private ScheduledExecutorService service;
    private AtomicInteger time = new AtomicInteger(0);
    private AtomicBoolean firstTick = new AtomicBoolean(true);

    public SmartTimer(IObserverManager observerManager) {
        this.observerManager = observerManager;
        observerManager.addObserver(GameStateChangeDto.class, this);
    }

    class Tick implements Runnable {
        @Override
        public void run() {
            if (firstTick.get()) {
                firstTick.set(false);
            } else {
                time.getAndIncrement();
            }
            sendNotify();
        }
    }

    @Override
    public void handleEvent(GameStateChangeDto dto) {
        switch (dto.getGameState()) {
            case RUN:
                continueTime();
                return;
            case READY_TO_RUN:
                resetTime();
            case LOSE:
            case WIN:
                pause();
        }
    }

    private void continueTime() {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Tick(), 0, 1, TimeUnit.SECONDS);
    }

    private void resetTime() {
        time.set(0);
        firstTick.set(true);
        sendNotify();
    }

    private void pause() {
        service.shutdown();
    }

    private void sendNotify() {
        observerManager.notifyObservers(new TimeChangeDto(time.get()));
    }

    @Override
    public Integer getTime() {
        return time.get();
    }
}
