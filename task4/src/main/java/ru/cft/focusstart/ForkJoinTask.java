package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

@Slf4j
@RequiredArgsConstructor
public class ForkJoinTask extends RecursiveTask<Task<FunctionCalculator>> {

    private static final int SIZE_TO_SPLIT = 100;//метод тыка, нужно смотреть относительно сложности задачи

    private final Task<FunctionCalculator> task;

    @Override
    protected Task<FunctionCalculator> compute() {
        if (task.getRawData().size() > SIZE_TO_SPLIT) {
            splitTask();
        } else {
            task.getRawData().forEach(value -> {
                Double result = task.getFunctionCalculator().calculateFunction(value);
                log.info("Просчитано значение: {}, в потоке: {}", value, Thread.currentThread().getName());
                task.getResultData().add(result);
                task.setSum(task.getSum() + result);
            });
        }
        return task;
    }

    private void splitTask() {
        int middleOfList = task.getRawData().size() / 2;
        ForkJoinTask leftTask = new ForkJoinTask(new Task<>(task.getRawData().subList(0, middleOfList), task.getFunctionCalculator()));
        ForkJoinTask rightTask = new ForkJoinTask(new Task<>(task.getRawData().subList(middleOfList, task.getRawData().size()), task.getFunctionCalculator()));
        leftTask.fork();
        rightTask.fork();
        task.getResultData().addAll(leftTask.join().getResultData());
        task.getResultData().addAll(rightTask.join().getResultData());
    }
}
