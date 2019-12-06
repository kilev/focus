package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    static void execute(Task task) {
        long startTime = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(new ForkJoinTask(task));
        logger.info("значения были посчитаны за: " + (System.currentTimeMillis() - startTime));
    }
}
