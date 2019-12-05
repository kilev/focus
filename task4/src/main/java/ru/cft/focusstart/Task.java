package ru.cft.focusstart;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
class Task {
    private List<Integer> rawData = new LinkedList<>();
    private List<Double> resultData = new ArrayList<>();
}
