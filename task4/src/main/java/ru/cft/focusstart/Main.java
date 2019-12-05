package ru.cft.focusstart;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer valueCount = 1000000; //UserQuestioner.askUserForValueCount();
        Integer threadCount = 1; //UserQuestioner.askUserForThreadCount();

        List<Integer> data = DataGenerator.generateData(valueCount);
        List<Task> tasks = new ArrayList<>();
        List<Thread> computingThreads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            tasks.add(new Task());
        }
        while (!data.isEmpty()) {
            for (Task task : tasks) {
                if (!data.isEmpty()) {
                    task.getRawData().add(data.get(0));
                    data.remove(0);
                }
            }
        }

        long startTime = System.currentTimeMillis();
        for (Task task : tasks) {
            Thread computingThread = new Thread(new Computer(task));
            computingThreads.add(computingThread);
            computingThread.start();
        }
        for (Thread computingThread : computingThreads) {
            try {
                computingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("значения были посчитаны за: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
}
