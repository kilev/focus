package ru.cft.focusstart.shape;

import ru.cft.focusstart.Printable;
import ru.cft.focusstart.Utils.DecimalFormatUtils;

public abstract class Shape implements Printable {

    abstract double calculateArea();

    abstract double calculatePerimeter();

    String getBasePrintText(String name, String units, String squareUnits) {
        return "Тип фигуры: " + name + System.lineSeparator()
                + "Площадь: " + DecimalFormatUtils.format(calculateArea()) + " " + squareUnits + " " + units + System.lineSeparator()
                + "Периметр: " + DecimalFormatUtils.format(calculatePerimeter()) + " " + units + System.lineSeparator();
    }
}
