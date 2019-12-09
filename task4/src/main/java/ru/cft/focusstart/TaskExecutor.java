package ru.cft.focusstart;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TaskExecutor {

    static void execute(Task<FunctionCalculator> task) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ForkJoinPool.commonPool().invoke(new ForkJoinTask(task));
        log.info("значения были посчитаны за: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + " миллисекунд.");
    }
}
