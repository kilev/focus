package ru.cft.focusstart;

import ru.cft.focusstart.shapes.Shape;

public class Main {

    public static void main(String[] args) {
        ShapeParamReader shapeParamReader = new ShapeParamReader();
        Shape shape = ShapeBuilder.Build(shapeParamReader.getShapeType(), shapeParamReader.getParam());
        if (shape != null){
            ShapeConsoleWriter.write(shape);
        }
    }
}
