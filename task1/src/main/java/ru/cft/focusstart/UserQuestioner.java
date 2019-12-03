package ru.cft.focusstart;

import java.util.Scanner;

class UserQuestioner {

    private static final int MIN_MULTIPLICATION_TABLE_SIZE = 1;
    private static final int MAX_MULTIPLICATION_TABLE_SIZE = 32;
    private static final String MESSAGE_TO_USER = "Введите размер таблицы("
            + MIN_MULTIPLICATION_TABLE_SIZE + "-" + MAX_MULTIPLICATION_TABLE_SIZE + "): ";

    private UserQuestioner() {
    }

    static int askUserForTableSize() {
        return askUserForInt(MESSAGE_TO_USER, MIN_MULTIPLICATION_TABLE_SIZE, MAX_MULTIPLICATION_TABLE_SIZE);
    }

    private static int askUserForInt(String message, int minValue, int maxValue) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);

        while (true) {
            String answer = in.next();

            try {
                int answerInt = Integer.parseInt(answer);

                if (answerInt < minValue || answerInt > maxValue) {
                    throw new NumberFormatException();
                }
                return answerInt;

            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА: Введены некорректные данные.");
                System.out.println("Вы должны ввести целое число в пределах от " + minValue + " до " + maxValue);
                System.out.println("Попробуйте ввести число занаво: ");
            }
        }
    }

}
