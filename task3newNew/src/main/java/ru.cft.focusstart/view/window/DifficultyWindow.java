package ru.cft.focusstart.view.window;

import ru.cft.focusstart.controller.DifficultyController;
import ru.cft.focusstart.difficulty.Difficulty;
import ru.cft.focusstart.difficulty.DifficultyConfig;

import javax.swing.*;
import java.awt.*;

import static ru.cft.focusstart.view.utils.ConstraintsUtils.getConstraints;

public class DifficultyWindow extends JDialog {

    private final static String DIFFICULTY_EAZY_NAME = "Легкий:";
    private final static String DIFFICULTY_MEDIUM_NAME = "Средний:";
    private final static String DIFFICULTY_HARD_NAME = "Тяжелый:";
    private final static String DIFFICULTY_CUSTOM_NAME = "Особый:";

    private final static String DIFFICULTY_SIZE_X_PARAM_NAME = "Высота:";
    private final static String DIFFICULTY_SIZE_Y_PARAM_NAME = "Ширина:";
    private final static String DIFFICULTY_BOMB_COUNT_PARAM_NAME = "Мины:";

    private final static String SAVE_BUTTON_NAME = "Сохранить";

    private final static Integer TEXT_FIELD_DEFAULT_SIZE = 3;

    private final JTextField customSizeXField = new JTextField(TEXT_FIELD_DEFAULT_SIZE);
    private final JTextField customSizeYField = new JTextField(TEXT_FIELD_DEFAULT_SIZE);
    private final JTextField customBombField = new JTextField(TEXT_FIELD_DEFAULT_SIZE);

    private Difficulty selectedDifficulty;

    public DifficultyWindow(JFrame parentFrame, String windowTitle, DifficultyController difficultyController) {
        super(parentFrame, windowTitle, true);
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());

        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton eazyButton = new JRadioButton(DIFFICULTY_EAZY_NAME);
        difficultyGroup.add(eazyButton);
        JRadioButton mediumButton = new JRadioButton(DIFFICULTY_MEDIUM_NAME);
        difficultyGroup.add(mediumButton);
        JRadioButton hardButton = new JRadioButton(DIFFICULTY_HARD_NAME);
        difficultyGroup.add(hardButton);
        JRadioButton customButton = new JRadioButton(DIFFICULTY_CUSTOM_NAME);
        difficultyGroup.add(customButton);
        customButton.setSelected(true);

        content.add(eazyButton, getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
        content.add(mediumButton, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
        content.add(hardButton, getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
        content.add(customButton, getConstraints(0, 4, 1, 1, GridBagConstraints.WEST));

        content.add(new JLabel(DIFFICULTY_SIZE_X_PARAM_NAME), getConstraints(1, 0, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.EAZY.getDifficultyConfig().getSizeX().toString()),
                getConstraints(1, 1, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.MEDIUM.getDifficultyConfig().getSizeX().toString()),
                getConstraints(1, 2, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.HARD.getDifficultyConfig().getSizeX().toString()),
                getConstraints(1, 3, 1, 1, GridBagConstraints.CENTER));
        customSizeXField.setText(difficultyController.getCurrentDifficulty().getDifficultyConfig().getSizeX().toString());
        customSizeXField.setHorizontalAlignment(JTextField.CENTER);
        content.add(customSizeXField, getConstraints(1, 4, 1, 1, GridBagConstraints.CENTER));

        content.add(new JLabel(DIFFICULTY_SIZE_Y_PARAM_NAME), getConstraints(2, 0, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.EAZY.getDifficultyConfig().getSizeY().toString()),
                getConstraints(2, 1, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.MEDIUM.getDifficultyConfig().getSizeY().toString()),
                getConstraints(2, 2, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.HARD.getDifficultyConfig().getSizeY().toString()),
                getConstraints(2, 3, 1, 1, GridBagConstraints.CENTER));
        customSizeYField.setText(difficultyController.getCurrentDifficulty().getDifficultyConfig().getSizeY().toString());
        customSizeYField.setHorizontalAlignment(JTextField.CENTER);
        content.add(customSizeYField, getConstraints(2, 4, 1, 1, GridBagConstraints.CENTER));

        content.add(new JLabel(DIFFICULTY_BOMB_COUNT_PARAM_NAME), getConstraints(3, 0, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.EAZY.getDifficultyConfig().getBombCount().toString()),
                getConstraints(3, 1, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.MEDIUM.getDifficultyConfig().getBombCount().toString()),
                getConstraints(3, 2, 1, 1, GridBagConstraints.CENTER));
        content.add(new JLabel(Difficulty.HARD.getDifficultyConfig().getBombCount().toString()),
                getConstraints(3, 3, 1, 1, GridBagConstraints.CENTER));
        customBombField.setText(difficultyController.getCurrentDifficulty().getDifficultyConfig().getBombCount().toString());
        customBombField.setHorizontalAlignment(JTextField.CENTER);
        content.add(customBombField, getConstraints(3, 4, 1, 1, GridBagConstraints.CENTER));

        JButton saveButton = new JButton(SAVE_BUTTON_NAME);
        content.add(saveButton, getConstraints(0, 5, 1, 4, GridBagConstraints.CENTER));

        saveButton.addActionListener(e -> {
            difficultyController.setDifficulty(selectedDifficulty);

        });
        eazyButton.addActionListener(e -> selectedDifficulty = Difficulty.EAZY);
        mediumButton.addActionListener(e -> selectedDifficulty = Difficulty.MEDIUM);
        hardButton.addActionListener(e -> selectedDifficulty = Difficulty.HARD);
        customButton.addActionListener(e -> {
            difficultyController.setCustomDifficultyConfig(new DifficultyConfig(Integer.parseInt(customSizeXField.getText()),
                    Integer.parseInt(customSizeYField.getText()),
                    Integer.parseInt(customBombField.getText())));
            difficultyController.setDifficulty(Difficulty.CUSTOM);
        });
        getContentPane().add(content);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
