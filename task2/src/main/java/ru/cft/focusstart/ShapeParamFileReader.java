package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.exceptions.ParamFileReadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ShapeParamFileReader implements ParamReader {

    private static final String INPUT_FILE_NAME = "task2/input.txt";
    private static final Logger logger = LoggerFactory.getLogger(ShapeParamFileReader.class);

    private String shapeType;
    private String[] param;

    ShapeParamFileReader(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            shapeType = bufferedReader.readLine();
            param = bufferedReader.readLine().split(" ");
            logger.trace("считан тип фигуры: {}, параметры {}", shapeType, param);
        } catch (IOException e) {
            String message = "Ошибка чтения файла.";
            logger.error(message, e);
            throw new ParamFileReadException(message, e);
        }
    }

    public String getShapeType() {
        return shapeType;
    }

    public String[] getParam() {
        return param;
    }
}
