package ru.cft.focusstart.view.content.label;

import lombok.Getter;
import lombok.Setter;
import ru.cft.focusstart.view.content.Resizable;

import javax.swing.*;

@Getter
@Setter
class NumericInfoLabel extends JLabel implements Resizable {

    private final static String NULL_DIGIT = "0";
    private final static String DEFAULT_TEXT = "000";
    private final static int MAX_VALUE = 999;
    private final static int MIN_VALUE = -999;

    private ImageIcon imageIcon;
    private Integer imageIconSize = 40;

    NumericInfoLabel() {
        super(DEFAULT_TEXT);
    }

    void setValue(Integer value) {
        int newValue = value;
        if (value > MAX_VALUE) {
            newValue = MAX_VALUE;
        } else if (value < MIN_VALUE) {
            newValue = MIN_VALUE;
        }
        setText(String.valueOf(newValue));
    }
}
