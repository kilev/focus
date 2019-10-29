package ru.cft.focusstart;

class TablePrinter {

    private static final char verticalSeparator = '|';
    private static final char horizontalSeparator = '-';
    private static final char nodeSeparator = '+';

    private short[][] array;
    private int size;

    private int cellSize;
    private int leftCellSize;

    TablePrinter(short[][] array, int size) {
        this.array = array;
        this.size = size;

        cellSize = String.valueOf(array[size - 1][size - 1]).length();
        leftCellSize = String.valueOf(size).length();
    }


    void print() {
        String separatorLine = getSeparatorLine();

        System.out.println(getFirstLine());
        System.out.println(separatorLine);

        for (int i = 0; i < size; i++) {
            System.out.print(getCell(i + 1, leftCellSize) + verticalSeparator);
            for (int j = 0; j < size; j++) {
                System.out.print(getCell(array[i][j], cellSize) + verticalSeparator);
            }
            System.out.println("\b");
            System.out.println(separatorLine);
        }

    }


    private String getCell(int value, int cellSize) {
        StringBuilder cellSpace = new StringBuilder();
        for (int i = 0; i < cellSize - String.valueOf(value).length(); i++) {
            cellSpace.append(' ');
        }
        return cellSpace.toString() + value;
    }


    private String getFirstLine() {
        StringBuilder firstLine = new StringBuilder();
        for (int i = 0; i < leftCellSize; i++) {
            firstLine.append(' ');
        }
        firstLine.append(verticalSeparator);
        for (int i = 0; i < size; i++) {
            firstLine.append(getCell(i + 1, cellSize)).append(verticalSeparator);
        }
        return firstLine.substring(0, firstLine.length() - 1);
    }


    private String getSeparatorLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < leftCellSize; i++)
            line.append(horizontalSeparator);

        line.append(nodeSeparator);
        StringBuilder floor = new StringBuilder();
        for (int i = 0; i < cellSize; i++)
            floor.append(horizontalSeparator);

        for (int i = 0; i < size; i++)
            line.append(floor).append(nodeSeparator);

        return line.substring(0, line.length() - 1);
    }

}
