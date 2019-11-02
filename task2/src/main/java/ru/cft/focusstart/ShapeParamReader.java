package ru.cft.focusstart;

import ru.cft.focusstart.shapes.ShapeType;

import java.io.*;

class ShapeParamReader {

    private static final String INPUT_FILE_NAME = "/input.txt";

    private ShapeType shapeType;
    private int[] param;

    ShapeParamReader() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(ShapeParamReader.class.getResourceAsStream(INPUT_FILE_NAME)))) {
            shapeType = ShapeType.valueOf(bufferedReader.readLine());
            String[] stringParam = bufferedReader.readLine().split(" ");

            param = new int[stringParam.length];
            for (int i = 0; i < stringParam.length; i++) {
                param[i] = Integer.parseInt(stringParam[i]);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    ShapeType getShapeType() {
        return shapeType;
    }

    int[] getParam() {
        return param;
    }
}
