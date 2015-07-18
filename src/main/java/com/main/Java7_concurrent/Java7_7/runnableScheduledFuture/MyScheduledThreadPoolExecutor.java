package com.main.Java7_concurrent.Java7_7.runnableScheduledFuture;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    6:57 at 2015/7/19
 * Email:   satansk@hotmail.com
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    /**
     * 装饰？
     *
     * @param runnable  任务
     * @param task  运行 Runnable 的任务
     * @param <V>   参数
     * @return  使用 runnable task 创建的 MyScheduledTask
     */
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        return new MyScheduledTask<>(runnable, null, this, task);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask myTask = (MyScheduledTask) task;
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        return myTask;
    }
}
