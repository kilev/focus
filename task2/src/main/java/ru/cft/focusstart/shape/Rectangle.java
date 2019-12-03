package ru.cft.focusstart.shape;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Rectangle extends Shape {

    private static final String NAME = "Прямоугольник";

    private final double width;
    private final double length;

    Rectangle(double sideA, double sideB) {
        validateParam(sideA, sideB);
        length = Math.max(sideA, sideB);
        width = Math.min(sideA, sideB);
    }

    private void validateParam(double sizeA, double sizeB) {
        if (sizeA <= 0 || sizeB <= 0) {
            throw new IllegalArgumentException("Отрицательный или нулевой параметр");
        }
    }

    @Override
    double calculateArea() {
        return width * length;
    }

    @Override
    double calculatePerimeter() {
        return (width * 2) + (length * 2);
    }

    double calculateDiagonalLength() {
        return Math.sqrt(Math.pow(width, 2) + Math.pow(length, 2));
    }

    @Override
    public String getPrintText(String units, String squareUnits) {
        return getBasePrintText(NAME, units, squareUnits)
                + "Длина диагонали: " + DecimalFormatUtils.format(calculateDiagonalLength()) + " " + units + System.lineSeparator()
                + "Длина: " + DecimalFormatUtils.format(length) + " " + units + System.lineSeparator()
                + "Ширина: " + DecimalFormatUtils.format(width) + " " + units;
    }

}
