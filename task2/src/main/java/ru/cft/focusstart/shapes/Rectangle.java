package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Rectangle extends Shape {

    private static final String name = "Прямоугольник";
    private static final int buildParamCount = 2;

    private final int width;
    private final int length;

    public Rectangle(int width, int length) {
        if(length > width) {
            this.length = length;
            this.width = width;
        } else {
            this.length = width;
            this.width = length;
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

    double calculateDiagonalLength(){
        return Math.sqrt(Math.pow(width, 2) + Math.pow(length, 2));
    }

    @Override
    public String getPrintText(String units, String squareUnits) {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(calculateArea()) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(calculatePerimeter()) + " " + units + "\n"
                + "Длина диагонали: " + DecimalFormatUtils.format(calculateDiagonalLength()) + " " + units + "\n"
                + "Длина: " + length + " " + units + "\n"
                + "Ширина: " + width + " " + units;
    }

    public static int getBuildParamCount() {
        return buildParamCount;
    }
}
