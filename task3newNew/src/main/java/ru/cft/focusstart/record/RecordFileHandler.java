package ru.cft.focusstart.record;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.view.iconService.IconStorage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class RecordFileHandler implements IRecordHandler {

    private static final Logger logger = LoggerFactory.getLogger(IconStorage.class);

    private static final String FILE_READ_ERROR_TEXT = "Ошибка чтения.";
    private static final String FILE_WRITE_ERROR_TEXT = "Ошибка записи.";

    private final static String FILE_PATH = "records.txt";

    private final static int RECORDS_COUNT = 3;

    private final static List<Difficulty> DIFFICULTIES = new ArrayList<>(Arrays.asList(Difficulty.EAZY, Difficulty.MEDIUM, Difficulty.HARD));

    private final static Type RECORDS_TYPE = new TypeToken<List<Record>>() {
    }.getType();

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
        if (!new File(FILE_PATH).exists()) {
            records = getZeroRecords();
            writeRecordsToFile(records);
            return records;
        }
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))) {
            records = new Gson().fromJson(reader, RECORDS_TYPE);
        } catch (IOException e) {
            logger.error(FILE_READ_ERROR_TEXT, e);
        }
        return records;
    }

    private List<Record> getZeroRecords() {
        List<Record> zeroRecords = new ArrayList<>();
        DIFFICULTIES.forEach(difficulty -> {
            for (int i = 0; i < RECORDS_COUNT; i++) {
                zeroRecords.add(new Record(null, difficulty, null));
            }
        });
        return zeroRecords;
    }

    private void writeRecordsToFile(List<Record> records) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(records, writer);
            writer.flush();
        } catch (IOException e) {
            logger.error(FILE_WRITE_ERROR_TEXT, e);
        }
    }

}
