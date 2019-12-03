package ru.cft.focusstart;

import com.beust.jcommander.IStringConverter;
import ru.cft.focusstart.writer.WriterType;

public class WriteTypeConverter implements IStringConverter<WriterType> {

    @Override
    public WriterType convert(String s) {
        return WriterType.valueOf(s);
    }
}
