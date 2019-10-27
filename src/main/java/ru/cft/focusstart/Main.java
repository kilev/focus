package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {

        int size = new UI().askUserForInt("Write table size(1-32): ", 1, 32);
        short[][] array = MultiplicationTableUtils.generateTable(size);
        new TablePrinter(array, size).print();

    }

}
