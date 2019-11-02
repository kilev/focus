package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Circle extends Shape {

    private static final String name = "Круг";
    private final int radius;
    private int diameter;

    public Circle(int radius, String units, String squareUnits) {
        this.radius = radius;
        this.units = units;
        this.squareUnits = squareUnits;
        calculateAllParam();
    }

    @Override
    void calculateAllParam() {
        diameter = radius * 2;
        perimeter = 2 * Math.PI * radius;
        area = Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(area) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(perimeter) + " " + units + "\n"
                + "Радиус: " + radius + " " + units + "\n"
                + "Диаметр: " + diameter + " " + units;
    }
}
