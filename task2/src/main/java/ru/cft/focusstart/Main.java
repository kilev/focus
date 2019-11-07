package ru.cft.focusstart;

import com.beust.jcommander.JCommander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.exceptions.ParamFileReadException;
import ru.cft.focusstart.exceptions.ShapeCreationException;
import ru.cft.focusstart.exceptions.ShapeFileWriteException;
import ru.cft.focusstart.shapes.ShapeBuilder;
import ru.cft.focusstart.writers.WriterFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String units = "мм";
    private static final String squareUnits = "кв.";
    private static final String PROGRAM_END = "Программа завершена.";
    private static final Args parameters = new Args();

    public static void main(String[] args) {
        logger.info("Программа запущена.");
        parseArgs(args);
        try {
            WriterFactory.create(parameters.getWriteType())
                    .write(ShapeBuilder.Build(new ShapeParamFileReader()), units, squareUnits);
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
                    .addObject(parameters)
                    .build()
                    .parse(args);
        } catch (Exception e) {
            System.out.println("некоректные аргументы командной строки");
        }
    }

}
