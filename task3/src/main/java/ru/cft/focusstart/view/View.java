package ru.cft.focusstart.view;

import ru.cft.focusstart.Observer.IObserverManager;
import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.controller.ModelController;
import ru.cft.focusstart.view.content.ContentManager;
import ru.cft.focusstart.view.content.menu.MenuManager;
import ru.cft.focusstart.view.window.MainWindow;

public class View implements IView {

    private final MainWindow mainWindow;
    private final MenuManager menuManager;
    private final ContentManager contentManager;

    public View(IObserverManager observerManager, ModelController modelController, DifficultyController difficultyController) {
        mainWindow = new MainWindow();
        contentManager = new ContentManager(observerManager, modelController, difficultyController, mainWindow);
        menuManager = new MenuManager(modelController, difficultyController, mainWindow, contentManager);

        modelController.newGame(difficultyController.getCurrentDifficulty());
    }

    @Override
    public String askUserForName() {
        return "";
    }
}
