package ru.cft.focusstart.forkJoin;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class ForkJoinTask extends RecursiveTask<List<Double>> {

    List<Integer> values;

    @Override
    protected List<Double> compute() {
        List<Double> resultValues = new LinkedList<>();
        List<ForkJoinTask> subTasks = new LinkedList<>();

        resultValues.add(someHardFunction(values.get(0)));
        values.remove(0);

        for (Integer value : values) {
            ForkJoinTask task = new ForkJoinTask(values);
            task.fork();
            subTasks.add(task);
        }

        for (ForkJoinTask task : subTasks) {
//            resultValues.add(task.join());
        }
        return resultValues;
    }

    private double someHardFunction(Integer value) {
        return Math.log(Math.cos(Math.sin(Math.sqrt(Math.atan(value) * 100000))));
    }
}
