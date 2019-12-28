package ru.cft.focusstart.timer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.cft.focusstart.event.GameStateChangeEvent;
import ru.cft.focusstart.event.TimeChangeEvent;
import ru.cft.focusstart.observer.IObserverManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class SmartTimer implements ITimer {

    private final IObserverManager observerManager;

    private ScheduledExecutorService service;
    private AtomicInteger time = new AtomicInteger(0);
    private AtomicBoolean firstTick = new AtomicBoolean(true);

    @Inject
    public SmartTimer(IObserverManager observerManager) {
        this.observerManager = observerManager;

        observerManager.addObserver(GameStateChangeEvent.class, event -> {
            switch (event.getGameState()) {
                case RUN:
                    continueTime();
                    return;
                case READY_TO_RUN:
                    resetTime();
                case LOSE:
                case WIN:
                    pause();
            }
        });
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
        observerManager.notifyObservers(new TimeChangeEvent(time.get()));
    }

    @Override
    public Integer getTime() {
        return time.get();
    }
}
