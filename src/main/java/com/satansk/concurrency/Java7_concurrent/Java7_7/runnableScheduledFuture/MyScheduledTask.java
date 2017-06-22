package com.satansk.concurrency.Java7_concurrent.Java7_7.runnableScheduledFuture;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    6:26 at 2015/7/19
 * Email:   satansk@hotmail.com
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    private RunnableScheduledFuture<V> task;
    private ScheduledThreadPoolExecutor executor;
    private long period;
    private long startDate;

    public MyScheduledTask(Runnable runnable, V result,
                           ScheduledThreadPoolExecutor executor, RunnableScheduledFuture<V> task) {
        super(runnable, result);
        this.executor = executor;
        this.task = task;
    }

    /**
     * 1. 周期任务，则更新 startDate 为任务下次执行的时间，
     *      然后将其任务添加到 ScheduledThreadPoolExecutor 的队列中
     */
    @Override
    public void run() {
        if (isPeriodic() && ! executor.isShutdown()) {
            startDate = new Date().getTime() + period;
            executor.getQueue().add(this);
        }

        System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
        System.out.printf("MyScheduledTask: Is Periodic:%s\n", isPeriodic());
        // 执行任务
        super.runAndReset();
        System.out.printf("Post-MyScheduledTask: %s\n", new Date());
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    /**
     * 1. 若为周期任务
     *      (1) startDate != 0，计算 startDate 与当前时间的差，返回
     *      (2) startDate == 0，返回 task 原始延迟
     * 2. 非周期任务，返回 task 中的原始延迟
     */
    @Override
    public long getDelay(TimeUnit unit) {
        if (! isPeriodic()) {
            return task.getDelay(unit);
        } else {
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                long delay = startDate - new Date().getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }

    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }
}
