package ru.cft.focusstart.shape;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.exception.ShapeCreationException;

import java.util.List;

public class ShapeBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ShapeBuilder.class);

    private ShapeBuilder() {
    }

    public static Shape build(ShapeType shapeType, List<Double> param) {
        try {
            switch (shapeType) {
                case CIRCLE:
                    return new Circle(param.get(0));
                case RECTANGLE:
                    return new Rectangle(param.get(0),
                            param.get(1));
                case TRIANGLE:
                    return new Triangle(param.get(0),
                            param.get(1),
                            param.get(2));
                default:
                    throw new UnsupportedOperationException("Неподдерживаемый тип фигуры: " + shapeType);
            }
        } catch (Exception e) {
            ShapeCreationException exception = new ShapeCreationException("Не удалось создать фигуру: " + e.getMessage(), e);
            logger.error("{}", exception.getMessage(), e);
            throw exception;
        }
    }

}
