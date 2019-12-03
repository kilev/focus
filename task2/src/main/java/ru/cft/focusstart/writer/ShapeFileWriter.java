package ru.cft.focusstart.writer;

import ru.cft.focusstart.Printable;
import ru.cft.focusstart.exception.ShapeFileWriteException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ShapeFileWriter implements ShapeWriter {

    private static final String OUTPUT_FILE_NAME = "output.txt";

    @Override
    public void write(Printable object, String units, String squareUnits) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
            bufferedWriter.write(object.getPrintText(units, squareUnits));
        } catch (IOException e) {
            throw new ShapeFileWriteException("Не удалось записать фигуру в файл: " + OUTPUT_FILE_NAME, e);
        }
    }

}
