package ru.cft.focusstart;

import com.google.inject.AbstractModule;
import ru.cft.focusstart.difficulty.DifficultyManager;
import ru.cft.focusstart.difficulty.IDifficultyManager;
import ru.cft.focusstart.model.service.IModelService;
import ru.cft.focusstart.model.service.ModelService;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.ObserverManager;
import ru.cft.focusstart.record.IRecordHandler;
import ru.cft.focusstart.record.IRecordProvider;
import ru.cft.focusstart.record.RecordFileHandler;
import ru.cft.focusstart.record.RecordProvider;
import ru.cft.focusstart.timer.ITimer;
import ru.cft.focusstart.timer.SmartTimer;
import ru.cft.focusstart.view.IView;
import ru.cft.focusstart.view.View;

public class BasicBindModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IObserverManager.class).to(ObserverManager.class);
        bind(IRecordHandler.class).to(RecordFileHandler.class);
        bind(ITimer.class).to(SmartTimer.class);
        bind(IView.class).to(View.class);
        bind(IRecordProvider.class).to(RecordProvider.class);
        bind(IDifficultyManager.class).to(DifficultyManager.class);
        bind(IModelService.class).to(ModelService.class);
    }
}
