package ru.cft.focusstart.shape;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

import static java.lang.Math.*;

public class Triangle extends Shape {

    private static final String NAME = "Треугольник";
    private static final char DEGREE_CHAR = '°';

    private final double sideA;
    private final double sideB;
    private final double sideC;

    Triangle(double sideA, double sideB, double sideC) {
        validateParam(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    private void validateParam(double sideA, double sideB, double sideC) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Отрицательный или нулевой параметр");
        }
        if (sideA + sideB <= sideC
                && sideA + sideC <= sideB
                && sideB + sideC <= sideA) {
            throw new IllegalArgumentException("Некорректные параметры треугольника.");
        }
    }

    private double calculateAngle(double sideA, double sideB, double sideC) {
        return acos((pow(sideB, 2) + pow(sideC, 2) - pow(sideA, 2)) / (2 * sideB * sideC)) * 180 / PI;
    }

    @Override
    double calculateArea() {
        double p = (sideA + sideB + sideC) / 2;
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String getPrintText(String units, String squareUnits) {
        return getBasePrintText(NAME, units, squareUnits)
                + "Длина стороны: " + sideA + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngle(sideA, sideB, sideC)) + DEGREE_CHAR + System.lineSeparator()
                + "Длина стороны: " + sideB + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngle(sideB, sideA, sideC)) + DEGREE_CHAR + System.lineSeparator()
                + "Длина стороны: " + sideC + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngle(sideC, sideA, sideB)) + DEGREE_CHAR;
    }

}
