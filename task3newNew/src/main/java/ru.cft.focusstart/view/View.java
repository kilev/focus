package ru.cft.focusstart.view;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.record.IRecordProvider;
import ru.cft.focusstart.view.content.ContentManager;
import ru.cft.focusstart.view.content.menu.MenuManager;
import ru.cft.focusstart.view.pane.UserNamePane;
import ru.cft.focusstart.view.window.MainWindow;

public class View implements IView {

    public View(IObserverManager observerManager, ModelController modelController, DifficultyController difficultyController, IRecordProvider recordProvider) {
        MainWindow mainWindow = new MainWindow();
        ContentManager contentManager = new ContentManager(observerManager, modelController, difficultyController, mainWindow);
        new MenuManager(modelController, difficultyController, recordProvider, mainWindow, contentManager);

        modelController.newGame(difficultyController.getCurrentDifficulty());
    }

    @Override
    public String askUserForName() {
        return UserNamePane.askUser();
    }
}
