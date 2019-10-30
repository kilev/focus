package ru.cft.focusstart;

public class Main {

    public static void main(String[] args) {
        int size = UserQuestioner.askUserForTableSize();
        TableBuilder tableBuilder = new TableBuilder(size, MultiplicationTableUtils.getMaxTableValue(size));

        ConsolePrinter.print(tableBuilder.getSeparatedFirstLine());
        ConsolePrinter.print(tableBuilder.getSeparatorLine());

        for (int i = 0; i < size; i++) {
            int[] lineValues = new int[size];
            for (int j = 0; j < size; j++) {
                lineValues[j] = MultiplicationTableUtils.getTableValue(i, j);
            }
            ConsolePrinter.print(tableBuilder.getLine(i, lineValues));
            ConsolePrinter.print(tableBuilder.getSeparatorLine());
        }
    }
}
