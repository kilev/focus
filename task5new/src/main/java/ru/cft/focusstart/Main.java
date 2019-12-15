package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        Storage<Task> storage = new Storage<>();
        for (int i = 0; i < PropertyManager.PRODUCERS_COUNT.getValue(); i++) {
            new Thread(new Producer(storage)).start();
        }
        for (int i = 0; i < PropertyManager.CONSUMER_COUNT.getValue(); i++) {
            new Thread(new Consumer(storage)).start();
        }
    }
}
