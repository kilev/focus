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

    private Integer computeValue(Integer value) {
        return someHardFunction(value);
    }

    private Integer someHardFunction(Integer value) {
        return (int) (Math.atan(value) * 100000);

    }
}
