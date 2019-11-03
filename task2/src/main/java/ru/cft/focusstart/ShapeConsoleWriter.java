package ru.cft.focusstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ShapeConsoleWriter {

    private static final Logger logger = LoggerFactory.getLogger(ShapeConsoleWriter.class);

    private ShapeConsoleWriter() {
    }

    static void write(Printable object, String units, String squareUnits) {
        try {
            System.out.println(object.getPrintText(units, squareUnits));
        } catch (NullPointerException e){
            logger.error("Ошибка печати.", e);
            throw e;
        }
    }

}
