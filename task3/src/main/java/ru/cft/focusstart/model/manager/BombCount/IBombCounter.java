package ru.cft.focusstart.model.manager.BombCount;

public interface IBombCounter {

    void setBombCount(Integer count);

    Integer getTotalBombCount();

    void bombFlaged();

    void bombUnflaged();
}
