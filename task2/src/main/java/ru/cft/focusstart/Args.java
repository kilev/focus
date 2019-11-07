package ru.cft.focusstart;

import com.beust.jcommander.Parameter;
import ru.cft.focusstart.writers.WriterType;

class Args {

    @Parameter(names = {"--writeType", "-wt"}, converter = WriteTypeConverter.class)
    private WriterType writeType = WriterType.CONSOLE;

    WriterType getWriteType() {
        return writeType;
    }
}
