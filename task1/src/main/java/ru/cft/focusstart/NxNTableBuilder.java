package ru.cft.focusstart;

class NxNTableBuilder {

    private static final char VERTICAL_SEPARATOR = '|';
    private static final char HORIZONTAL_SEPARATOR = '-';
    private static final char NODE_SEPARATOR = '+';
    private static final char SPACE = ' ';
    private static final char NEWLINE = '\n';

    private final int[][] table;
    private final int tableSize;
    private final int cellSize;
    private final int leftCellSize;
    private final String separatorLine;

    NxNTableBuilder(int[][] table, int maxValue) {
        this.table = table;
        this.tableSize = table.length;
        cellSize = String.valueOf(maxValue).length();
        leftCellSize = String.valueOf(tableSize).length();
        separatorLine = generateSeparatorLine();
    }

    String build() {
        StringBuilder stringBuilder = new StringBuilder()
                .append(getTopHeader());
        stringBuilder
                .delete(stringBuilder.length() - 1, stringBuilder.length())
                .append(NEWLINE)
                .append(separatorLine).append(NEWLINE);

        for (int i = 0; i < tableSize; i++) {
            stringBuilder.append(getCell(i + 1, leftCellSize));
            for (int j = 0; j < tableSize; j++) {
                stringBuilder.append(getCell(table[i][j], cellSize));
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length())
                    .append(NEWLINE)
                    .append(separatorLine)
                    .append(NEWLINE);
        }
        return String.valueOf(stringBuilder);
    }

    private String getCell(int value, int cellSize) {
        return String.valueOf(new StringBuilder()
                .append(getCellSpace(value, cellSize))
                .append(value)
                .append(VERTICAL_SEPARATOR));
    }

    private String getTopHeader() {
        StringBuilder stringBuilder = new StringBuilder()
                .append(getCellSpace(0, leftCellSize))
                .append(SPACE)
                .append(VERTICAL_SEPARATOR);
        for (int i = 0; i < tableSize; i++) {
            stringBuilder.append(getCell(i + 1, cellSize));
        }
        return String.valueOf(stringBuilder);
    }

    private String getCellSpace(int value, int cellSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cellSize - String.valueOf(value).length(); i++) {
            stringBuilder.append(SPACE);
        }
        return String.valueOf(stringBuilder);
    }

    private String generateSeparatorLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < leftCellSize; i++) {
            line.append(HORIZONTAL_SEPARATOR);
        }

        line.append(NODE_SEPARATOR);
        StringBuilder floor = new StringBuilder();
        for (int i = 0; i < cellSize; i++) {
            floor.append(HORIZONTAL_SEPARATOR);
        }

        for (int i = 0; i < tableSize; i++) {
            line.append(floor).append(NODE_SEPARATOR);
        }
        return line.substring(0, line.length() - 1);
    }

}
