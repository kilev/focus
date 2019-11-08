package ru.cft.focusstart.shape;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Rectangle extends Shape {

    private static final String NAME = "Прямоугольник";

    private final int width;
    private final int length;

    Rectangle(int length1, int length2) {
        validateParam(length1, length2);
        length = Math.max(length1, length2);
        width = Math.min(length1, length2);
    }

    private void validateParam(int length1, int length2) {
        if (length1 <= 0 || length2 <= 0) {
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
        return super.getBasePrintText(NAME, units, squareUnits)
                + "Длина диагонали: " + DecimalFormatUtils.format(calculateDiagonalLength()) + " " + units + "\n"
                + "Длина: " + length + " " + units + "\n"
                + "Ширина: " + width + " " + units;
    }

}
