package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        Task task = new Task(DataGenerator.generateData());
        TaskExecutor.execute(task);
        TaskResultValidator.validate(task);
    }
}
