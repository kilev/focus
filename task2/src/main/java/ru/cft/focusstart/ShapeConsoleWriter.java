package ru.cft.focusstart;

import ru.cft.focusstart.shapes.Shape;

class ShapeConsoleWriter {

    private ShapeConsoleWriter() {
    }

    static void write(Shape shape) {
        System.out.println(shape.toString());
    }
}
