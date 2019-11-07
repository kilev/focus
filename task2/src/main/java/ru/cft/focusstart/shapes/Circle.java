package ru.cft.focusstart.shapes;

public class Circle extends Shape {

    private static final String NAME = "Круг";
    private static final int buildParamCount = 1;
    private final int radius;


    Circle(int radius) {
        validateParam(radius);
        this.radius = radius;
    }

    private void validateParam(int radius) {
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
        return super.getBasePrintText(NAME, calculateArea(), calculatePerimeter(), units, squareUnits)
                + "Радиус: " + radius + " " + units + "\n"
                + "Диаметр: " + calculateDiameter() + " " + units;
    }

    static int getBuildParamCount() {
        return buildParamCount;
    }
}
