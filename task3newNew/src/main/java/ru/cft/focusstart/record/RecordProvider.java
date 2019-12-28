package ru.cft.focusstart.record;

import com.google.inject.Inject;

import java.util.List;

public class RecordProvider implements IRecordProvider {

    private final IRecordHandler recordHandler;

    @Inject
    public RecordProvider(IRecordHandler recordHandler) {
        this.recordHandler = recordHandler;
    }

    @Override
    public List<Record> getRecords() {
        return recordHandler.getRecords();
    }
}
