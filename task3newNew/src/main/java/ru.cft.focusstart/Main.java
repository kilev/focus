package ru.cft.focusstart;

import com.google.inject.Guice;
import ru.cft.focusstart.dependencyInjection.BasicBindModule;
import ru.cft.focusstart.record.RecordWriter;

public class Main {

    public static void main(String[] args) {
        Guice.createInjector(new BasicBindModule()).getInstance(RecordWriter.class);
    }
}
