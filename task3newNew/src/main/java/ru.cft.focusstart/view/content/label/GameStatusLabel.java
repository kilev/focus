package ru.cft.focusstart.view.content.label;

import ru.cft.focusstart.dto.GameStateChangeDto;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;

import javax.swing.*;

public class GameStatusLabel extends JLabel implements Observer<GameStateChangeDto> {

    private static final String WIN_TEXT = "WIN:)";
    private static final String LOSE_TEXT = "LOSE:(";
    private static final String DEFAULT_TEXT = "";

    public GameStatusLabel(IObserverManager observerManager) {
        observerManager.addObserver(GameStateChangeDto.class, this);
    }

    @Override
    public void handleEvent(GameStateChangeDto dto) {
        switch (dto.getGameState()) {
            case READY_TO_RUN:
            case RUN:
                setText(DEFAULT_TEXT);
                break;
            case WIN:
                setText(WIN_TEXT);
                break;
            case LOSE:
                setText(LOSE_TEXT);
                break;
        }
    }
}
