package ru.cft.focusstart.shape;

import java.util.ArrayList;
import java.util.List;

class ShapeParamParser {

    private final ShapeType parsedType;
    private final List<Integer> parsedParam = new ArrayList<>();

    ShapeParamParser(String shapeTypeStr, String[] paramStr) {
        parsedType = ShapeType.valueOf(shapeTypeStr);

        for (int i = 0; i < paramStr.length; i++) {
            parsedParam.add(Integer.parseInt(paramStr[i]));
        }
    }

    ShapeType getShapeType() {
        return parsedType;
    }

    List<Integer> getParam() {
        return parsedParam;
    }
}
