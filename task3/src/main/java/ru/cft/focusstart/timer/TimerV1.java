package ru.cft.focusstart.timer;

public class TimerV1 {

}// implements Runnable, TimerAPI, GameStateObserver {
//
//    private final List<TimerObserver> observers = new ArrayList<>();
//
//    private int currentTime = 0;
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(1000);
//                currentTime++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    @Override
//    public void handleEvent(GameStateChangeDTO gameStateChangeDTO) {
//        if (gameStateChangeDTO.getGameState() == GameStateType.RUN) {
//            reset();
//        }
//    }
//
//    private void reset() {
//        currentTime = 0;
//    }
//
//    public int getTime(){
//        return currentTime;
//    }
//
//    @Override
//    public void addObserver(TimerObserver timerObserver) {
//        observers.add(timerObserver);
//    }
//
//    @Override
//    public void removeObserver(TimerObserver timerObserver) {
//        observers.remove(timerObserver);
//    }
//
//    @Override
//    public void notifyObservers() {
//        observers.forEach(observer -> observer.handleEvent(new TimeChanchedDTO(currentTime)));
//    }

