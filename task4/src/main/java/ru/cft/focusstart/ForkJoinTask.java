package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

@RequiredArgsConstructor
public class ForkJoinTask extends RecursiveTask<Task> {

    private static final Logger logger = LoggerFactory.getLogger(ForkJoinTask.class);
    private static final int SIZE_TO_SPLIT = 100;

    private final Task task;

    @Override
    protected Task compute() {
        if (task.getRawData().size() > SIZE_TO_SPLIT) {
            splitValues();
        } else {
            task.getRawData().forEach(value -> {
                Double result = someHardFunction(value);
                task.getResultData().add(result);
                task.setSum(task.getSum() + result);
            });
        }
        return task;
    }

    private void splitValues() {
        int middleOfList = task.getRawData().size() / 2;
        ForkJoinTask leftTask = new ForkJoinTask(new Task(task.getRawData().subList(0, middleOfList)));
        ForkJoinTask rightTask = new ForkJoinTask(new Task(task.getRawData().subList(middleOfList, task.getRawData().size())));
        leftTask.fork();
        rightTask.fork();
        task.getResultData().addAll(leftTask.join().getResultData());
        task.getResultData().addAll(rightTask.join().getResultData());
    }

    private Double someHardFunction(Integer value) {
        Double resultValue = null;
        for (int i = 0; i < 10000; i++) {
            resultValue = Math.cos(value);
        }
        logger.info("Computed value: " + value + " in " + Thread.currentThread().getName());
        return resultValue;
    }
}
