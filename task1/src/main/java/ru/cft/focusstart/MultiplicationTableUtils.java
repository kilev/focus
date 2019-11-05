package ru.cft.focusstart;

class MultiplicationTableUtils {

    private MultiplicationTableUtils() {
    }

    static int getMaxValue(int size) {
        return size * size;
    }

    static int[][] generateTable(int size) {
        int[][] table = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

}
