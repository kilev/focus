package ru.cft.focusstart.record;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RecordProvider implements IRecordProvider {

    private final IRecordHandler recordHandler;

    @Override
    public List<Record> getRecords() {
        return recordHandler.getRecords();
    }
}
