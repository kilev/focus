package ru.cft.focusstart;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
class DataGenerator {

    static List<Integer> generateData(Integer valueCount) {
        List<Integer> generatedData = new ArrayList<>();
        for (int i = 1; i < valueCount; i++) {
            generatedData.add(i);
        }
        return generatedData;
    }
}
