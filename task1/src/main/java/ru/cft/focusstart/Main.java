package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        int size = UserQuestioner.askUserForTableSize();
        TableBuilder tableBuilder = new TableBuilder(MultiplicationTableUtils.generateTable(size),
                MultiplicationTableUtils.getMaxValue(size));
        ConsolePrinter.print(tableBuilder.build());
    }
}
