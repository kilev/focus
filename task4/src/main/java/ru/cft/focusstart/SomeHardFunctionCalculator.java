package ru.cft.focusstart;

public class SomeHardFunctionCalculator implements FunctionCalculator {

    private static final int ITERATE_COUNT = 10_000;

    @Override
    public Double calculateFunction(Integer value) {
        Double resultValue = null;
        for (int i = 0; i < ITERATE_COUNT; i++) {
            resultValue = Math.cos(value);
        }
        return resultValue;
    }
}
