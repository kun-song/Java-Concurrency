package com.satansk.concurrency.Java7_concurrent.Java7_4.newScheduledThreadPool_delay;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    8:50 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * ScheduledThreadPoolExecutor 作为 ThreadPoolExecutor 的子类，拥有其所有方法，
 * 但是 Java 建议其只用于 任务调度。
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 只含有一个线程的线程池
        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("Main: starting at %s\n", new Date());
        for (int i = 0; i < 5; i++) {
            /**
             * schedule() 三个参数：
             * 1. 执行的任务
             * 2. 延迟时间
             * 3. 时间单位
             *
             * 每个任务有自己的延迟时间
             */
            executor.schedule(
                    new Task("Task--" + i),
                    (long) (i + Math.random() * 10),
                    TimeUnit.SECONDS);
        }
        executor.shutdown();

        /**
         * 等待所有任务完成
         */
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.printf("Main: end at %s\n", new Date());
    }
}
