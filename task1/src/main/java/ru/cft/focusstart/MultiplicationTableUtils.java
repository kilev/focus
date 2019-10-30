package ru.cft.focusstart;

class MultiplicationTableUtils {

    private MultiplicationTableUtils() {
    }

    static int getTableValue(int indexX, int indexY) {
        return (indexX + 1) * (indexY + 1);
    }

    static int getMaxTableValue(int size) {
        return size * size;
    }

}
