package ru.cft.focusstart.record;

import ru.cft.focusstart.difficulty.Difficulty;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordFileHandler implements IRecordHandler {

    private final static File FILE = new File("records.txt");

    private final static Integer RECORDS_COUNT = 3;

    private final static List<Difficulty> difficulties = new ArrayList<>(Arrays.asList(Difficulty.EAZY, Difficulty.MEDIUM, Difficulty.HARD));

    @Override
    public List<Record> getRecords() {
        return readRecordsFromFile();
    }

    @Override
    public void saveRecords(List<Record> records) {
        writeRecordsToFile(records);
    }

    private List<Record> readRecordsFromFile() {
        List<Record> records = new ArrayList<>();
        if (!FILE.exists()) {
            records = getZeroRecords();
            writeRecordsToFile(records);
            return records;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE))) {
            while (true) {
                Record record = (Record) objectInputStream.readObject();
                records.add(record);
            }
        } catch (ClassCastException | ClassNotFoundException | IOException ignore) {
            return records;
        }
    }

    private List<Record> getZeroRecords() {
        List<Record> zeroRecords = new ArrayList<>();
        difficulties.forEach(difficulty -> {
            for (int i = 0; i < RECORDS_COUNT; i++) {
                zeroRecords.add(new Record(null, difficulty, null));
            }
        });
        return zeroRecords;
    }

    private void writeRecordsToFile(List<Record> records) {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
            for (Record record : records) {
                objectOutputStream.writeObject(record);
            }
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
