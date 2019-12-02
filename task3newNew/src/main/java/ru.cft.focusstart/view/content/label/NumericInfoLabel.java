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
    private final static Integer MAX_VALUE = 999;

    private ImageIcon imageIcon;
    private Integer imageIconSize = 40;

    NumericInfoLabel() {
        super(DEFAULT_TEXT);
    }

    void setValue(Integer value) {
        if (value > MAX_VALUE) {
            setText(String.valueOf(MAX_VALUE));
        } else if (Math.abs(value) > MAX_VALUE) {
            setText("-" + MAX_VALUE.toString().substring(1));
        } else if (value < 0) {
            setText("-" + NULL_DIGIT.repeat(String.valueOf(MAX_VALUE).length() - String.valueOf(value).length()) + Math.abs(value));
        } else {
            setText(NULL_DIGIT.repeat(String.valueOf(MAX_VALUE).length() - String.valueOf(value).length()) + value);
        }
    }
}
