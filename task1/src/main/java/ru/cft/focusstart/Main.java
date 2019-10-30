package ru.cft.focusstart;

public class Main {

    private static final String MESSAGE_TO_USER = "Write table size(1-32): ";
    private static final int MIN_MULTIPLICATION_TABLE_SIZE = 1;
    private static final int MAX_MULTIPLICATION_TABLE_SIZE = 32;

    public static void main(String[] args) {
        int size = UserQuestioner.askUserForInt(MESSAGE_TO_USER, MIN_MULTIPLICATION_TABLE_SIZE, MAX_MULTIPLICATION_TABLE_SIZE);
        TableBuilder tableBuilder = new TableBuilder(size, MultiplicationTableUtils.getTableValue(size, size));

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
