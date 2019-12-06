package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TaskResultValidator {

    private static final Logger logger = LoggerFactory.getLogger(TaskResultValidator.class);

    static void validate(Task task) {
        for (int i = 0; i < task.getRawData().size(); i++) {
            Double resultValue = task.getResultData().get(i);
            Double theoreticalValue = Math.cos(task.getRawData().get(i));
            if (!resultValue.equals(theoreticalValue)) {
                logger.info("Wrong result: required value" + theoreticalValue + " ,founded: " + resultValue);
                return;
            }
        }
        logger.info("results successful validated.");
    }

}
