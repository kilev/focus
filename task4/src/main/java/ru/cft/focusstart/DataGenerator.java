package ru.cft.focusstart;

import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
class DataGenerator {

    static List<Integer> generateData(Integer valueCount) {
        List<Integer> generatedData = new LinkedList<>();
        for (int i = 1; i < valueCount; i++) {
            generatedData.add(i);
        }
        return generatedData;
    }
}
