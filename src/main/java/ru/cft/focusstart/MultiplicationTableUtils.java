package ru.cft.focusstart;

class MultiplicationTableUtils {

    static short[][] generateTable(int size) {
        short[][] array = new short[size][size];

        // генерируем значения таблицы выше главной диагонали(включительно)
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                array[i][j] = (short) ((i + 1) * (j + 1));
            }
        }

        // копируем значения в область ниже главной диагонали
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                array[i][j] = array[j][i];
            }
        }

        return array;
    }

}
