package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.exception.ParamFileReadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ShapeParamFileReader implements ParamReader {

    private static final Logger logger = LoggerFactory.getLogger(ShapeParamFileReader.class);

    private static final String INPUT_FILE_NAME = "task2/input.txt";
    private static final String READ_EXCEPTION_TEXT = "Ошибка чтения файла.";

    private final String shapeType;
    private final String[] param;

    ShapeParamFileReader(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            shapeType = bufferedReader.readLine();
            param = bufferedReader.readLine().split("\\s+");
            logger.trace("считан тип фигуры: {}, параметры {}", shapeType, param);
        } catch (IOException e) {
            logger.error(READ_EXCEPTION_TEXT, e);
            throw new ParamFileReadException(READ_EXCEPTION_TEXT, e);
        }
    }

    public String getShapeType() {
        return shapeType;
    }

    public String[] getParam() {
        return param;
    }
}
