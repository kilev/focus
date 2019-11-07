package ru.cft.focusstart.shapes;

import java.util.ArrayList;
import java.util.List;

class ShapeParamParser {

    private ShapeType parsedType;
    private List<Integer> parsedParam = new ArrayList<>();

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
