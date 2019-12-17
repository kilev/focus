package ru.cft.focusstart.record;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.event.GameStateChangeEvent;
import ru.cft.focusstart.model.gamestate.GameStateType;
import ru.cft.focusstart.observer.IObserverManager;
import ru.cft.focusstart.observer.Observer;
import ru.cft.focusstart.timer.ITimer;
import ru.cft.focusstart.view.IView;

import java.util.List;

@Singleton
public class RecordWriter implements Observer<GameStateChangeEvent> {

    private final IView view;
    private final ITimer timer;
    private final IRecordHandler recordHandler;

    @Inject
    public RecordWriter(IObserverManager observerManager, IView view, ITimer timer, IRecordHandler recordHandler) {
        observerManager.addObserver(GameStateChangeEvent.class, this);
        this.view = view;
        this.timer = timer;
        this.recordHandler = recordHandler;
    }

    @Override
    public void handleEvent(GameStateChangeEvent event) {
        if (event.getGameState() == GameStateType.WIN) {
            checkToNewRecord(timer.getTime(), event.getDifficulty());
        }
    }

    private void checkToNewRecord(Integer score, Difficulty difficulty) {
        List<Record> records = recordHandler.getRecords();

        Integer subRecordsStartIndex = null;
        int subRecordsCount = 0;

        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getDifficulty() == difficulty) {
                if (subRecordsStartIndex == null) {
                    subRecordsStartIndex = i;
                }
                subRecordsCount++;
            }
        }

        List<Record> subRecords = records.subList(subRecordsStartIndex, subRecordsCount);
        subRecords.stream()
                .filter(record -> record.getScore() == null || record.getScore() > score)
                .findFirst()
                .ifPresent(record -> {
                    subRecords.add(subRecords.indexOf(record)
                            , new Record(view.askUserForName(), difficulty, score));
                    subRecords.remove(subRecords.size() - 1);
                });
        recordHandler.saveRecords(records);
    }

}
