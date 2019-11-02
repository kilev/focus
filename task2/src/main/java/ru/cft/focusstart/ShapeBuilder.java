package ru.cft.focusstart;


import ru.cft.focusstart.shapes.*;

class ShapeBuilder {

    private static final String units = "мм";
    private static final String squareUnits = "кв.";

    private ShapeBuilder() {
    }

    static Shape Build(ShapeType type, int[] param) {
        switch (type) {
            case CIRCLE:
                return new Circle(param[0], units, squareUnits);
            case RECTANGLE:
                return new Rectangle(param[0], param[1], units, squareUnits);
            case TRIANGLE :
                return new Triangle(param[0], param[1], param[2], units, squareUnits);
            default:
                return null;
        }
    }

}
