package ru.cft.focusstart.shape;

import ru.cft.focusstart.Printable;
import ru.cft.focusstart.Utils.DecimalFormatUtils;

abstract class Shape implements Printable {

    abstract double calculateArea();

    abstract double calculatePerimeter();

    String getBasePrintText(String name, String units, String squareUnits) {
        return "Тип фигуры: " + name + "\n"
                + "Площадь: " + DecimalFormatUtils.format(calculateArea()) + " " + squareUnits + " " + units + "\n"
                + "Периметр: " + DecimalFormatUtils.format(calculatePerimeter()) + " " + units + "\n";
    }
}
