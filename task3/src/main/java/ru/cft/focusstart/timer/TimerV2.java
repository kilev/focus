package ru.cft.focusstart.timer;

public class TimerV2
//        implements TimerAPI, Observer<GameStateChangeDTO>
{
//
//    private AtomicInteger currentTime = new AtomicInteger(0);
//    private Boolean run = true;
//    private final Thread time = new Thread(new Time());
//
//    public TimerV2() {
//        time.start();
//    }
//
//    class Time implements Runnable {
//        @Override
//        public void run() {
//            while (!run) {
//                synchronized (run) {
//                    try {
//                        run.wait();
//                        while (run) {
////                            Thread.sleep(1000);
//                            currentTime.getAndDecrement();
//                            notifyObservers();
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void handleEvent(GameStateChangeDTO gameStateChangeDTO) {
//        switch (gameStateChangeDTO.getGameState()) {
//            case RUN:
//                reset();
//                return;
//            case LOSE:
//            case WIN:
//                run = false;
//        }
//    }
//
//    private void reset() {
//        currentTime = 0;
//        synchronized (run) {
//            run = true;
//            run.notify();
//        }
//    }
//
//    @Override
//    public int getTime() {
//        return currentTime;
//    }

}
