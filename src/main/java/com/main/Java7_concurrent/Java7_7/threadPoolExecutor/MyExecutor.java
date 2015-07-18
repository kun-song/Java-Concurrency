package com.main.Java7_concurrent.Java7_7.threadPoolExecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    18:24 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * 继承 ThreadPoolExecutor，覆盖其默认方法。
 *  1. shutdown() shutdownNow() 在 ThreadPoolExecutor 中有默认实现，所以需要调用 super()
 *  2. beforeExecute() afterExecute() 在 ThreadPoolExecutor 中为空，所以无需调用 super
 */
public class MyExecutor extends ThreadPoolExecutor {
    // 记录任务的开始时间，使用 Runnable/Callable 的 hashCode 作为 key
    private ConcurrentHashMap<String, Date> startTimeMap;

    public MyExecutor(int corePoolSize,
                      int maximumPoolSize,
                      long keepAliveTime,
                      TimeUnit unit,
                      BlockingQueue<Runnable> workQueue) {
        // 调用父类的构造器
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.startTimeMap = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.printf("shutdown: shutdown().\n");
        /**
         * 1. getCompletedTaskCount(): 完成任务的估计值
         * 2. 因为任务和线程的状态动态改变，所以只是估计值
         * 3. 保证返回任务数不递减
         */
        System.out.printf("shutdown: completed tasks: %d\n", getCompletedTaskCount());
        /**
         * 1. getActiveCount(): 正在执行的任务估计值
         */
        System.out.printf("shutdown: active tasks: %d\n", getActiveCount());  // 进行中
        /**
         * 1. Executor.getQueue() 得到本 Executor 的任务队列
         * 2. 不影响任务执行
         */
        System.out.printf("shutdown: pending tasks: %d\n", getQueue().size());    // 待处理

        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("shutdownNow: shutdownNow().\n");
        System.out.printf("shutdownNow: completed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("shutdownNow: active tasks: %d\n", getActiveCount());
        System.out.printf("shutdownNow: pending tasks: %d\n", getQueue().size());

        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("beforeExecute --> thread %s is going to execute task %s\n", t.getName(), r.hashCode());
        startTimeMap.put(String.valueOf(r.hashCode()), new Date());
    }

    /**
     * 将任务的结果和执行时间输出
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> future = (Future<?>) r;
        System.out.println("-----------------------------");
        System.out.printf("afterExecute()\n");
        try {
            System.out.printf("afterExecute: result = %s\n", future.get());
            long duration = new Date().getTime() - startTimeMap.get(String.valueOf(r.hashCode())).getTime();
            System.out.printf("afterExecute: duration = %d\n", duration);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------");
    }
}
