package ru.cft.focusstart;

import lombok.NoArgsConstructor;

import java.util.Scanner;

@NoArgsConstructor
class UserQuestioner {

    private static final Integer MIN_VALUE_COUNT = 1;
    private static final Integer MAX_VALUE_COUNT = Integer.MAX_VALUE;
    private static final String ASK_USER_FOR_VALUE_COUNT_MESSAGE = "Введите количество чисел("
            + MIN_VALUE_COUNT + "-" + MAX_VALUE_COUNT + "): ";

    private static final Integer MIN_THREADS_COUNT = 1;
    private static final Integer MAX_THREADS_COUNT = 10;
    private static final String ASK_USER_FOR_THREAD_COUNT_MESSAGE = "Введите количество потоков("
            + MIN_THREADS_COUNT + "-" + MAX_THREADS_COUNT + "): ";

    static Integer askUserForValueCount() {
        return askUserForInteger(ASK_USER_FOR_VALUE_COUNT_MESSAGE, MIN_VALUE_COUNT, MAX_VALUE_COUNT);
    }

    static Integer askUserForThreadCount() {
        return askUserForInteger(ASK_USER_FOR_THREAD_COUNT_MESSAGE, MIN_THREADS_COUNT, MAX_THREADS_COUNT);
    }

    private static Integer askUserForInteger(String message, Integer minValue, Integer maxValue) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);

        while (true) {
            String answer = in.next();
            try {
                Integer answerInt = Integer.parseInt(answer);

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
