package com.satansk.concurrency.Java7_concurrent.Java7_4.newScheduledThreadPool_period;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    9:38 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * ScheduledThreadPoolExecutor 执行周期任务
 *
 * 1. ScheduledThreadPoolExecutor.scheduledAtFixedRate(任务，启动延迟，间隔时间，时间单位)
 * 2. 返回 ScheduledFuture<>，可以使用 getDelay() 获取下次任务执行剩余时间
 *
 * 3. shutdown() 之后，周期任务结束
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("Main: Starting at: %s\n\n",new Date());

        Task task = new Task("Task");
        /**
         * 1. 参数意义: (任务，启动延迟，间隔时间，时间单位)
         * 2. 如果 task 需要 5s 完成，而执行间隔是 3s，则同一时刻不会并发执行，下次任务将会被 更加延时启动
         * 3. 因为 task 是非参数化的 Runnable，所以 ScheduledFuture 使用 ? 作为参数
         */
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        /**
         * 1. 每 500毫秒 查看开始下一次任务执行的剩余时间
         * 2. 第一次任务延迟 1s，后面间隔 2s 启动一次
         * 3. 从 for 输出看，两次执行之间的时间是固定的
         */
        for (int i = 0; i < 50; i++) {
            /**
             * ScheduledFuture.getDelay() 能够获取该 定时任务 距离下次执行的剩余时间
             */
            System.out.printf("Main: %d check: delay: %d\n", i, future.getDelay(TimeUnit.MILLISECONDS));
            // main 线程睡眠 500 毫秒
            TimeUnit.MILLISECONDS.sleep(500);
        }

        /**
         * 与其他 Executor 的关闭不同，ScheduledExecutor.shutdown() 之后，定时任务结束，不再执行。
         */
        executor.shutdown();

        // 睡眠 5s，查看 shutdown() 之后，周期任务是否还会执行
        TimeUnit.SECONDS.sleep(5);
        System.out.printf("\nMain: Finished at: %s\n",new Date());
    }
}
