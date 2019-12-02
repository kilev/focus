package ru.cft.focusstart.view.utils;

import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
public class ConstraintsUtils {

    private final static Insets DEFAULT_INSETS = new Insets(2, 2, 2, 2);
    private final static Integer DEFAULT_WEIGHT = 1;

    public static GridBagConstraints getConstraints(Integer gridx, Integer gridy, Integer gridheight, Integer gridwidth, Integer anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = DEFAULT_INSETS;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;
        constraints.anchor = anchor;
        constraints.weightx = DEFAULT_WEIGHT;
        constraints.weighty = DEFAULT_WEIGHT;
        return constraints;
    }
}
