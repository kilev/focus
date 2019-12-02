package ru.cft.focusstart.record;

import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.dto.GameStateChangeDto;
import ru.cft.focusstart.model.GameState.GameStateType;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.view.IView;

import java.util.List;

public class RecordWriter implements Observer<GameStateChangeDto> {

    private final IView view;
    private final IRecordHandler recordHandler;

    public RecordWriter(IObserverManager observerManager, IView view, IRecordHandler recordHandler) {
        observerManager.addObserver(GameStateChangeDto.class, this);
        this.view = view;
        this.recordHandler = recordHandler;
    }

    @Override
    public void handleEvent(GameStateChangeDto dto) {
        if (dto.getGameState() == GameStateType.WIN) {
            checkToNewRecord(dto.getTime(), dto.getDifficulty());
        }
    }

    private void checkToNewRecord(Integer score, Difficulty difficulty) {
        List<Record> records = recordHandler.getRecords();
        for (Record record : records) {
            if ((record.getDifficulty() == difficulty) && record.getScore() == null || record.getScore() < score) {
                records.set(records.indexOf(record), new Record(view.askUserForName(), difficulty, score));
                recordHandler.saveRecords(records);
                return;
            }
        }
    }

}
