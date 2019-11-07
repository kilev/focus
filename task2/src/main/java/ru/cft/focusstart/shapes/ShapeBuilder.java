package ru.cft.focusstart.shapes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.ParamReader;
import ru.cft.focusstart.exceptions.ShapeCreationException;

public class ShapeBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ShapeBuilder.class);

    private ShapeBuilder() {
    }

    public static Shape Build(ParamReader paramReader) {
        try {
            ShapeParamParser parser = new ShapeParamParser(paramReader.getShapeType(), paramReader.getParam());

            switch (parser.getShapeType()) {
                case CIRCLE:
                    return new Circle(parser.getParam().get(0));
                case RECTANGLE:
                    return new Rectangle(parser.getParam().get(0),
                            parser.getParam().get(1));
                case TRIANGLE:
                    return new Triangle(parser.getParam().get(0),
                            parser.getParam().get(1),
                            parser.getParam().get(2));
                default:
                    throw new UnsupportedOperationException("Неподдерживаемый тип фигуры: " + parser.getShapeType());
            }
        } catch (Exception e) {
            ShapeCreationException exception = new ShapeCreationException("Не удалось создать фигуру: " + e.getMessage(), e);
            logger.error("{}", exception.getMessage(), e);
            throw exception;
        }
    }

}
