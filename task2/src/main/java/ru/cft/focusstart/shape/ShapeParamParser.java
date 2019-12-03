package ru.cft.focusstart.shape;

import ru.cft.focusstart.exception.ParamParseException;

import java.util.ArrayList;
import java.util.List;

public class ShapeParamParser {

    private final ShapeType parsedType;
    private final List<Double> parsedParam = new ArrayList<>();

    public ShapeParamParser(String shapeTypeStr, List<String> paramStr) {
        try {
            parsedType = ShapeType.valueOf(shapeTypeStr);

            for (String param : paramStr) {
                parsedParam.add(Double.parseDouble(param));
            }
        } catch (IllegalArgumentException e) {
            throw new ParamParseException("Не удалось распознать аргументы фигуры.", e);
        }

    }

    public ShapeType getShapeType() {
        return parsedType;
    }

    public List<Double> getParam() {
        return parsedParam;
    }
}
