package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Computer implements Runnable {

    private final Task task;

    @Override
    public void run() {
        computeTask();
    }

    private void computeTask() {
        for (Integer value : task.getRawData()) {
            task.getResultData().add(computeValue(value));
        }
    }

    private Double computeValue(Integer value) {
        return someHardFunction(value);
    }

    private double someHardFunction(Integer value) {
        return Math.log(Math.cos(Math.sin(Math.sqrt(Math.atan(value) * 100000))));
    }
}
