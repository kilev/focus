package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Utils.DecimalFormatUtils;

import static java.lang.Math.*;

public class Triangle extends Shape {

    private static final String NAME = "Треугольник";
    private static final char degreeChar = '°';
    private static final int buildParamCount = 3;

    private final int sideA;
    private final int sideB;
    private final int sideC;

    Triangle(int sideA, int sideB, int sideC) {
        validateParam(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    private void validateParam(int sideA, int sideB, int sideC) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Отрицательный или нулевой параметр");
        }
        if (sideA + sideB <= sideC
                && sideA + sideC <= sideB
                && sideB + sideC <= sideA) {
            throw new IllegalArgumentException("Некорректные параметры треугольника.");
        }
    }

    double calculateAngleA(){
        return acos((pow(sideB, 2) + pow(sideC, 2) - pow(sideA, 2)) / (2 * sideB * sideC)) * 180 / PI;
    }

    double calculateAngleB(){
        return acos((pow(sideA, 2) + pow(sideC, 2) - pow(sideB, 2)) / (2 * sideA * sideC)) * 180 / PI;
    }

    double calculateAngleC(){
        return acos((pow(sideB, 2) + pow(sideA, 2) - pow(sideC, 2)) / (2 * sideB * sideA)) * 180 / PI;
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
        return super.getBasePrintText(NAME, calculateArea(), calculatePerimeter(), units, squareUnits)
                + "Длина стороны: " + sideA + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngleA()) + degreeChar + "\n"
                + "Длина стороны: " + sideB + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngleB()) + degreeChar + "\n"
                + "Длина стороны: " + sideC + " " + units + ", противолежащий угол: " + DecimalFormatUtils.format(calculateAngleC()) + degreeChar;
    }

    static int getBuildParamCount() {
        return buildParamCount;
    }
}
