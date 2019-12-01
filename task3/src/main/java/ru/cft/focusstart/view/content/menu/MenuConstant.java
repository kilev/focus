package ru.cft.focusstart.view.content.menu;

import lombok.NoArgsConstructor;

@NoArgsConstructor
class MenuConstant {

    static final String NEW_LINE = System.lineSeparator();

    static final String MENU_GAME = "Игра";
    static final String NEW_GAME_ITEM = "Новая игра";
    static final String DISPLAY = "Отоброжение";
    static final String HIGH_SCORE_ITEM = "Рекорды";
    static final String EXIT_GAME = "Закрыть";

    static final String OPTIONS_ITEM = "Настройки";
    static final String DIFFICULTY_EASY = "Легкий";
    static final String DIFFICULTY_MEDIUM = "Средний";
    static final String DIFFICULTY_HARD = "Тяжелый";
    static final String DIFFICULTY_CUSTOM = "Произвольный";

    static final String MENU_REFERENCE = "Справка";
    static final String HOW_TO_PLAY_ITEM = "Как играть?";

    static final String HOW_TO_PLAY_TEXT = "Число в ячейке показывает, сколько мин скрыто вокруг данной ячейки . Это число поможет понять вам, где находятся безопасные ячейки, а где находятся бомбы." + NEW_LINE +
            "Если рядом с открытой ячейкой есть пустая ячейка, то она откроется автоматически." + NEW_LINE +
            "Если вы открыли ячейку с миной, то игра проиграна.." + NEW_LINE +
            "Что бы пометить ячейку, в которой находится бомба, нажмите её правой кнопкой мыши." + NEW_LINE +
            "После того, как вы отметите все мины, можно навести курсор на открытую ячейку и нажать правую и левую кнопку мыши одновременно. Тогда откроются все свободные ячейки вокруг неё" + NEW_LINE +
            "Если в ячейке указано число, оно показывает, сколько мин скрыто в восьми ячейках вокруг данной. Это число помогает понять, где находятся безопасные ячейки." + NEW_LINE +
            "Игра продолжается до тех пор, пока вы не откроете все не заминированные ячейки.";
}
