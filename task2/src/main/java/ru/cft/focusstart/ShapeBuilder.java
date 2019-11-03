package ru.cft.focusstart;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.Exceptions.CreateShapeException;
import ru.cft.focusstart.shapes.*;

class ShapeBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ShapeBuilder.class);

    private ShapeBuilder() {
    }

    static Shape Build(ParamReader paramReader) {
        try {
            ShapeParamValidator validator = new ShapeParamValidator(paramReader.getShapeType(), paramReader.getParam());

            switch (validator.getValidShapeType()) {
                case CIRCLE:
                    if (validator.validateParamCount(Circle.getBuildParamCount())) {
                        return new Circle(validator.getValidParam()[0]);

                    }
                case RECTANGLE:
                    if (validator.validateParamCount(Rectangle.getBuildParamCount())) {
                        return new Rectangle(validator.getValidParam()[0],
                                validator.getValidParam()[1]);
                    }
                case TRIANGLE:
                    if (validator.validateTriangleSides()
                    && validator.validateParamCount(Triangle.getBuildParamCount())) {
                        return new Triangle(validator.getValidParam()[0],
                                validator.getValidParam()[1],
                                validator.getValidParam()[2]);
                    }
            }
            return null;
        } catch (Exception e) {
            CreateShapeException exception = new CreateShapeException("Не удалось создать фигуру.", e);
            logger.error("{}", exception.getMessage(), e);
            throw exception;
        }
    }

}
