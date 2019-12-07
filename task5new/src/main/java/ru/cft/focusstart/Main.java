package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        PropertyManager propertyManager = new PropertyManager();

        Storage storage = new Storage(propertyManager.getProperty("storage.size"));
        for (int i = 0; i < propertyManager.getProperty("producer.count"); i++) {
            new Thread(new Producer(storage, propertyManager.getProperty("producer.produceTime"))).start();
        }
        for (int i = 0; i < propertyManager.getProperty("consumer.count"); i++) {
            new Thread(new Consumer(storage, propertyManager.getProperty("consumer.consumeTime"))).start();
        }
    }
}
