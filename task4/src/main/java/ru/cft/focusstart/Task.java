package ru.cft.focusstart;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Task<T extends FunctionCalculator> {
    private final List<Integer> rawData;
    private final T functionCalculator;
    private final List<Double> resultData = new ArrayList<>();
    private Double sum = 0.0;
}

