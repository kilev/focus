package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

import static java.lang.Math.*;

public class Triangle extends Shape {

    private static final String name = "Треугольник";
    private static final char degreeChar = '°';

    private final int sideA;
    private final int sideB;
    private final int sideC;

    private double angleA;
    private double angleB;
    private double angleC;

    public Triangle(int sideA, int sideB, int sideC, String units, String squareUnits) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.units = units;
        this.squareUnits = squareUnits;
        calculateAllParam();
    }

    @Override
    void calculateAllParam() {

        angleA = acos((pow(sideB, 2) + pow(sideC, 2) - pow(sideA, 2)) / (2 * sideB * sideC)) * 180 / PI;
        angleC = acos((pow(sideB, 2) + pow(sideA, 2) - pow(sideC, 2)) / (2 * sideB * sideA)) * 180 / PI;
        angleB = 180 - (angleA + angleC);

        perimeter = sideA + sideB + sideC;

        double p = (sideA + sideB + sideC) / 2;
        area = sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public String toString() {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(area) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(perimeter) + " " + units + "\n"
                + "Длина стороны: " + sideA + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(angleA) + degreeChar + "\n"
                + "Длина стороны: " + sideB + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(angleB) + degreeChar + "\n"
                + "Длина стороны: " + sideC + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(angleC) + degreeChar;
    }
}
