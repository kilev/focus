package ru.cft.focusstart.shape;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Circle extends Shape {

    private static final String NAME = "Круг";
    private final double radius;

    Circle(double radius) {
        validateParam(radius);
        this.radius = radius;
    }

    private void validateParam(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Отрицательный или нулевой параметр");
        }
    }

    @Override
    double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    double calculateDiameter() {
        return radius * 2;
    }

    @Override
    public String getPrintText(String units, String squareUnits) {
        return getBasePrintText(NAME, units, squareUnits)
                + "Радиус: " + DecimalFormatUtils.format(radius) + " " + units + System.lineSeparator()
                + "Диаметр: " + DecimalFormatUtils.format(calculateDiameter()) + " " + units;
    }

}
