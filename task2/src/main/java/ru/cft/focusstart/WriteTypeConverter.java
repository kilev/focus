package ru.cft.focusstart;

import com.beust.jcommander.IStringConverter;
import ru.cft.focusstart.writers.WriterType;

public class WriteTypeConverter implements IStringConverter<WriterType> {

    @Override
    public WriterType convert(String s) {
        try {
            return WriterType.valueOf(s);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный аргумент командной строки: " + s);
            System.exit(1);
        }
        return null;
    }
}
