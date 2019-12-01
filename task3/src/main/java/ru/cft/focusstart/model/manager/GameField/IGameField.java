package ru.cft.focusstart.model.manager.GameField;

public interface IGameField {
    void openCell(Integer x, Integer y);

    void flagCell(Integer x, Integer y);

    void newGameField(Integer sizeX, Integer sizeY);
}
