package ru.cft.focusstart;

import java.util.Scanner;

class UserQuestioner {

    private UserQuestioner() {}

    static int askUserForInt(String message, int minValue, int maxValue) {
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
                System.out.println("ERROR: Введены некорректные данные.");
                System.out.println("Вы должны ввести целое число в пределах от " + minValue + " до " + maxValue);
                System.out.println("Попробуйте ввести число занаво: ");
            }
        }
    }

}
