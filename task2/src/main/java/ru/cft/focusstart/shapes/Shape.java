package ru.cft.focusstart.shapes;

import ru.cft.focusstart.Printable;

public abstract class Shape implements Printable {
    abstract double calculateArea();
    abstract double calculatePerimeter();
}
