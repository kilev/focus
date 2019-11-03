package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.shapes.ShapeType;

class ShapeParamValidator {

    private static final Logger logger = LoggerFactory.getLogger(ShapeParamValidator.class);

    private ShapeType validShapeType;
    private int[] validParam;

    ShapeParamValidator(String shapeTypeStr, String paramStr) {
            validShapeType = ShapeType.valueOf(shapeTypeStr);

            String[] stringParam = paramStr.split(" ");
            validParam = new int[stringParam.length];
            for (int i = 0; i < stringParam.length; i++) {
                validParam[i] = Integer.parseInt(stringParam[i]);
                if (validParam[i] <= 0) throw new NumberFormatException();
            }
    }

    boolean validateTriangleSides() {
        if (validParam[0] + validParam[1] > validParam[2]
                && validParam[0] + validParam[2] > validParam[1]
                && validParam[1] + validParam[2] > validParam[0]) {
            return true;
        } else {
            throw new IllegalArgumentException("Некорректные параметры треугольника.");
        }
    }

    boolean validateParamCount(int requestedAmount) {
        if (requestedAmount == validParam.length) {
            return true;
        } else {
            throw new IllegalArgumentException("Количество параметров отлично от нужного.");
        }
    }

    ShapeType getValidShapeType() {
        return validShapeType;
    }

    int[] getValidParam() {
        return validParam;
    }
}
