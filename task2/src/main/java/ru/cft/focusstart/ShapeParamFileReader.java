package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.Exceptions.ParamFileReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ShapeParamFileReader implements ParamReader {

    private static final String INPUT_FILE_NAME = "/input.txt";
    private static final Logger logger = LoggerFactory.getLogger(ShapeParamFileReader.class);

    private String shapeType;
    private String param;

    ShapeParamFileReader(){
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(ShapeParamValidator.class.getResourceAsStream(INPUT_FILE_NAME)))) {
            shapeType = bufferedReader.readLine();
            param = bufferedReader.readLine();
        } catch (IOException |NullPointerException e) {
            String message = "Ошибка чтения файла.";
            logger.error(message, e);
            throw new ParamFileReadException(message, e);
        }
    }

    public String getShapeType() {
        return shapeType;
    }

    public String getParam() {
        return param;
    }
}
