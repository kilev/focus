package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        int size = UserQuestioner.askUserForTableSize();
        ConsolePrinter.print(new NxNTableBuilder(MultiplicationTableUtils.generateTable(size)
                , MultiplicationTableUtils.getMaxValue(size)).build());
    }
}
