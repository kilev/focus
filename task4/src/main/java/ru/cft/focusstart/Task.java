package ru.cft.focusstart;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Task {
    private final List<Integer> rawData;
    private List<Double> resultData = new ArrayList<>();
    private Double sum = 0.0;
}
