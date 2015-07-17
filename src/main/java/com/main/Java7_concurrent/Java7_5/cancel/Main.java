package com.main.Java7_concurrent.Java7_5.cancel;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:32 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayGenerator generator = new ArrayGenerator();
        int[] array = generator.generateArray(1000);
        int TARGET = 5;

        TaskManager taskManager = new TaskManager();
        ForkJoinPool pool = new ForkJoinPool();
        SearchNumberTask task = new SearchNumberTask(array, 0, 1000, TARGET, taskManager);

        pool.execute(task);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Main: the program has finished!");
    }
}
