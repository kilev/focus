package ru.cft.focusstart.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerV3 implements ITimer
        //implements Observered<TimeChanchedDTO>
{

    private final Timer timer = new Timer();
    private volatile int currentTime;

    private void reset() {
        currentTime = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                currentTime++;
            }
        }, 0, 1000);
    }

    @Override
    public int getTime() {
        return 0;
    }

//    @Override
//    public GameStateModel getGlobalGateWay() {
//        return null;
//    }
//
//    @Override
//    public void sendNotify(EventType eventType, TimeChanchedDTO dto) {
//
//    }
}
