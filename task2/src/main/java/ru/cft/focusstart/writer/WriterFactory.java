package ru.cft.focusstart.writer;

public class WriterFactory {

    private WriterFactory() {
    }

    public static ShapeWriter create(WriterType writeType) {
        switch (writeType) {
            case FILE:
                return new ShapeFileWriter();
            case CONSOLE:
                return new ShapeConsoleWriter();
            default:
                throw new UnsupportedOperationException("Неподдерживаемый тип печати: " + writeType);
        }
    }
}
