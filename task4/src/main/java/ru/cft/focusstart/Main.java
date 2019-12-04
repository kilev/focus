package ru.cft.focusstart;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer valueCount = UserQuestioner.askUserForValueCount();
        Integer threadCount = UserQuestioner.askUserForThreadCount();

        List<Integer> data = DataGenerator.generateData(valueCount);
        List<Task> tasks = new ArrayList<>();

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
        for (Task task : tasks) {
            new Thread(new Computer(task)).start();
        }
    }
}
