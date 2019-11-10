package ru.cft.focusstart;

import com.beust.jcommander.JCommander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.exception.ParamFileReadException;
import ru.cft.focusstart.exception.ShapeCreationException;
import ru.cft.focusstart.exception.ShapeFileWriteException;
import ru.cft.focusstart.shape.Shape;
import ru.cft.focusstart.shape.ShapeBuilder;
import ru.cft.focusstart.shape.ShapeParamParser;
import ru.cft.focusstart.writer.WriterFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String UNITS = "мм";
    private static final String SQUARE_UNITS = "кв.";
    private static final String PROGRAM_END = "Программа завершена.";
    private static final Args PARAMETERS = new Args();

    public static void main(String[] args) {
        logger.info("Программа запущена.");
        parseArgs(args);
        try {
            ShapeParamFileReader paramReader = new ShapeParamFileReader();
            ShapeParamParser paramParser = new ShapeParamParser(paramReader.getShapeType(), paramReader.getParam());
            Shape shape = ShapeBuilder.build(paramParser.getShapeType(), paramParser.getParam());
            WriterFactory.create(PARAMETERS.getWriteType()).write(shape, UNITS, SQUARE_UNITS);

            System.out.println("Программа отработала корректно.");
        } catch (ParamFileReadException | ShapeCreationException | ShapeFileWriteException | UnsupportedOperationException e) {
            System.out.println("Программа отработала с ошибкой: " + e.getMessage());
            logger.error("", e);
        } finally {
            System.out.println(PROGRAM_END);
            logger.info(PROGRAM_END);
        }
    }

    private static void parseArgs(String[] args) {
        try {
            JCommander.newBuilder()
                    .addObject(PARAMETERS)
                    .build()
                    .parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Некоректные аргументы командной строки, выбраны параметры поумолчанию.");
        }
    }

}
