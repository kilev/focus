package ru.cft.focusstart.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    ForkJoinPoolTest() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
    }

    class Task extends RecursiveTask<Integer> {
//        List<Integer> values

        @Override
        protected Integer compute() {
            return null;
        }
    }
}
