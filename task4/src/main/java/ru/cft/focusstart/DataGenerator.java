package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DataGenerator {

    private final static Integer VALUE_COUNT = 100000; //UserQuestioner.askUserForValueCount();

    static List<Integer> generateData() {
        List<Integer> generatedData = new ArrayList<>();
        for (int i = 1; i < VALUE_COUNT; i++) {
            generatedData.add(i);
        }
        return generatedData;
    }
}
