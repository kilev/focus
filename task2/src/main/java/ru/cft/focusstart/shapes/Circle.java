package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

public class Circle extends Shape {

    private static final String name = "Круг";
    private static final int buildParamCount = 1;
    private final int radius;


    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }

    double calculateDiameter(){
        return radius * 2;
    }

    @Override
    public String getPrintText(String units, String squareUnits) {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(calculateArea()) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(calculatePerimeter()) + " " + units + "\n"
                + "Радиус: " + radius + " " + units + "\n"
                + "Диаметр: " + calculateDiameter() + " " + units;
    }

    public static int getBuildParamCount() {
        return buildParamCount;
    }
}
