package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.Exceptions.CreateShapeException;
import ru.cft.focusstart.Exceptions.ParamFileReadException;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String units = "мм";
    private static final String squareUnits = "кв.";

    public static void main(String[] args) {
        logger.info("Программа запущена.");
        try {
            ShapeConsoleWriter.write(ShapeBuilder.Build(new ShapeParamFileReader()), units, squareUnits);
            logger.info("Программа отработала корректно.");
        } catch (CreateShapeException | ParamFileReadException e) {
            System.out.println("Программа отработала с ошибкой: " + e.getMessage());
        } finally {
            logger.info("Программа завершена.");
        }

    }
}
