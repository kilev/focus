package ru.cft.focusstart;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.difficulty.DifficultyManager;
import ru.cft.focusstart.difficulty.IDifficultyManager;
import ru.cft.focusstart.model.service.IModelService;
import ru.cft.focusstart.model.service.ModelService;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.ObserverManager;
import ru.cft.focusstart.record.IRecordHandler;
import ru.cft.focusstart.record.RecordFileHandler;
import ru.cft.focusstart.record.RecordProvider;
import ru.cft.focusstart.record.RecordWriter;
import ru.cft.focusstart.timer.ITimer;
import ru.cft.focusstart.timer.SmartTimer;
import ru.cft.focusstart.view.IView;
import ru.cft.focusstart.view.View;

public class Main {

    public static void main(String[] args) {
        IObserverManager observerManager = new ObserverManager();
        IDifficultyManager difficultyManager = new DifficultyManager();
        IModelService modelService = new ModelService(observerManager);
        IRecordHandler recordHandler = new RecordFileHandler();
        IView view = new View(observerManager, new ModelController(modelService), new DifficultyController(difficultyManager), new RecordProvider(recordHandler));
        ITimer timer = new SmartTimer(observerManager);
        new RecordWriter(observerManager, view, timer, recordHandler);
    }
}
