package ru.cft.focusstart;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TaskResultValidator {

    static void validate(Task<FunctionCalculator> task) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < task.getRawData().size(); i++) {
            Double resultValue = task.getResultData().get(i);
            Double theoreticalValue = task.getFunctionCalculator().calculateFunction(task.getRawData().get(i));

            if (!resultValue.equals(theoreticalValue)) {
                log.info("Неправильное значение, ожидалось: {}, найдено: {}.", theoreticalValue, resultValue);
                return;
            }
            log.info("Проверен результат для значения: {}", i);
        }
        log.info("результаты успешно проверены. Проверка заняла: {} миллисекунд. в однопоточном режиме.", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

}
