package ru.cft.focusstart.record;

import java.util.List;

public interface IRecordHandler {

    List<Record> getRecords();

    void saveRecords(List<Record> records);
}
