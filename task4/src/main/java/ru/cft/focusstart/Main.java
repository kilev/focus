package ru.cft.focusstart;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Task<FunctionCalculator> task = new Task<>(DataGenerator.getDataStream().collect(Collectors.toList()), new SomeHardFunctionCalculator());
        TaskExecutor.execute(task);
        TaskResultValidator.validate(task);
    }
}
