package com.main.Java7_concurrent.Java7_5.exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:39 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[100];
        Task task = new Task(array, 0, 100);

        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(task);
        pool.shutdown();
        /**
         * 1. 等待 task 任务执行完毕，才继续向下执行 main()
         * 2. 如果不等待，则 main 线程快速向下执行，下面的异常判断时，task 任务还没有执行到抛出异常那个时间点，
         *      所以无法检测到异常
         * 3. 等待 task 任务完成后，就能检测到异常了
         */
        pool.awaitTermination(1, TimeUnit.DAYS);

        /**
         * 1. ForkJoinTask 即使抛出异常也不会结束执行
         * 2. 需要手动检测是否发生了异常
         */
        if (task.isCompletedAbnormally()) {
            System.out.println("Main: An exception has thrown.\n");
            /**
             * getException() 获取 ForkJoinTask 任务的异常
             */
            System.out.printf("Main: %s\n", task.getException());
            System.out.printf("Main: result = %d\n", task.join());
        }
    }
}
