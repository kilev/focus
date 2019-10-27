package ru.cft.focusstart;

import org.apache.commons.lang.math.NumberUtils;

import java.util.Scanner;

class UI {

    private Scanner in;

    UI() {
        in = new Scanner(System.in);
    }

    int askUserForInt(String message, int minValue, int maxValue) {

        while (true) {
            System.out.print(message);
            String answer = in.next();

            if (NumberUtils.isNumber(answer)) { // проверяем является ли ответ от польхователя целым числом
                int answerInt = 0;

                try {
                    answerInt = Integer.parseInt(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Error: The number is too big.");
                }

                if (answerInt >= minValue && answerInt <= maxValue)
                    return answerInt;
                else
                    System.out.println("Error: Value must be between " + minValue + " and " + maxValue);
            } else
                System.out.println("Error: You need to enter a number.");
        }
    }

}
