package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();
        for (int i = 0; i < PropertyManager.PRODUCERS_COUNT.getValue(); i++) {
            new Thread(new Producer(storage), Producer.getNAME() + " - " + i).start();
        }
        for (int i = 0; i < PropertyManager.CONSUMER_COUNT.getValue(); i++) {
            new Thread(new Consumer(storage), Consumer.getNAME() + " - " + i).start();
        }
    }
}
