package ru.cft.focusstart;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.Observer.ObserverManager;
import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.difficulty.DifficultyManager;
import ru.cft.focusstart.difficulty.IDifficulty;
import ru.cft.focusstart.model.IModel;
import ru.cft.focusstart.model.Model;
import ru.cft.focusstart.record.IRecorder;
import ru.cft.focusstart.timer.ITimer;
import ru.cft.focusstart.view.View;

public class Main {

    public static void main(String[] args) {
        IObserverManager observerManager = new ObserverManager();
        IDifficulty difficultyManager = new DifficultyManager();
        ITimer timer = null;
        //    = new TimerV3(observerManager);
        IModel model = new Model(observerManager, timer);
        View view = new View(observerManager, new ModelController(model), new DifficultyController(difficultyManager));
        IRecorder recorder;
    }
}
