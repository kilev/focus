package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DataGenerator {

    private final static Integer VALUE_COUNT = 100_000;
    private final static Integer SEED_VALUE = 1;

    static Stream<Integer> getDataStream() {
        return Stream.iterate(SEED_VALUE, (Integer i) -> ++i).limit(VALUE_COUNT);
    }
}
