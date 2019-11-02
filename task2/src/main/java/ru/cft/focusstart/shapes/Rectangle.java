package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Rectangle extends Shape {

    private static final String name = "Прямоугольник";

    private final int width;
    private final int length;

    private double diagonalLength;

    public Rectangle(int width, int length, String units, String squareUnits) {
        if(length > width) {
            this.length = length;
            this.width = width;
        } else {
            this.length = width;
            this.width = length;
        }
        this.units = units;
        this.squareUnits = squareUnits;
        calculateAllParam();
    }

    @Override
    void calculateAllParam() {
        diagonalLength = Math.sqrt(Math.pow(width, 2) + Math.pow(length, 2));
        perimeter = (width * 2) + (length * 2);
        area = width * length;
    }

    @Override
    public String toString() {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(area) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(perimeter) + " " + units + "\n"
                + "Длина диагонали: " + DecimalFormatUtils.format(diagonalLength) + " " + units + "\n"
                + "Длина: " + length + " " + units + "\n"
                + "Ширина: " + width + " " + units;
    }
}
