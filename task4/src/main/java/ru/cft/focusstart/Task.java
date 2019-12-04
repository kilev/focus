package ru.cft.focusstart;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Task {
    private List<Integer> rawData = new ArrayList<>();
    private List<Integer> resultData = new ArrayList<>();
}
