package ru.cft.focusstart;

class TableBuilder {

    private static final char VERTICAL_SEPARATOR = '|';
    private static final char HORIZONTAL_SEPARATOR = '-';
    private static final char NODE_SEPARATOR = '+';

    private final int tableSize;
    private final int cellSize;
    private final int leftCellSize;
    private final String separatorLine;

    TableBuilder(int tableSize, int maxElement) {
        this.tableSize = tableSize;
        cellSize = String.valueOf(maxElement).length();
        leftCellSize = String.valueOf(tableSize).length();
        separatorLine = generateSeparatorLine();
    }

    String getSeparatorLine() {
        return separatorLine;
    }

    String getLine(int lineIndex, int[] lineValues) {
        StringBuilder line = new StringBuilder();
        line.append(getCell(lineIndex + 1, leftCellSize));
        for (int value : lineValues) {
            line.append(getCell(value, cellSize));
        }
        return String.valueOf(line.delete(line.length() - 1, line.length()));
    }

    String getSeparatedFirstLine() {
        StringBuilder firstLine = new StringBuilder();
        for (int i = 0; i < leftCellSize; i++) {
            firstLine.append(' ');
        }

        firstLine.append(VERTICAL_SEPARATOR);
        for (int i = 0; i < tableSize; i++) {
            firstLine.append(getCell(i + 1, cellSize));
        }
        return firstLine.substring(0, firstLine.length() - 1);
    }

    private String getCell(int value, int cellSize) {
        StringBuilder cellSpace = new StringBuilder();
        for (int i = 0; i < cellSize - String.valueOf(value).length(); i++) {
            cellSpace.append(' ');
        }
        return String.valueOf(cellSpace.append(value).append(VERTICAL_SEPARATOR));
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
