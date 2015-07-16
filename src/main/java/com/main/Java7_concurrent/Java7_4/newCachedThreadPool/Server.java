package com.main.Java7_concurrent.Java7_4.newCachedThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author:  satansk
 * Date:    19:20 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * Executor:
 *      1. 将 task 的创建和执行分开
 *
 *      2. 必须 明确 关闭，否则 Executor 将会继续执行，当没有 task 可以计算时，Executor 会继续等待新任务，
 *          不会结束运行，从而整个 Java 程序不会结束。（除非所有的非守护线程都完成）
 *
 * newCachedThreadPool() 创建缓存线程池。
 *
 * 缓存线程池:
 *      1. 当有新任务来时，将会创建新线程来执行任务
 *      2. 执行任务的线程在任务结束之后，重新变成可用状态，可以被重新使用（线程复用）
 *
 * 缓存线程池的缺点：
 *      1. 基本容量为 0，最大容量为 Integer.MAX_VALUE
 *      2. 所以一有新任务就会创建新线程，如果任务过多，将会是系统超载
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        /**
         * 1. newCachedThreadPool() 返回 ExecutorService 对象，需要强制转换
         *
         * 2. new ThreadPoolExecutor(
         *          0,  // 基本容量
         *          Integer.MAX_VALUE,  // 最大容量
         *          60L,    // 存活时间
         *          TimeUnit.SECOND,
         *          new SynchronizedQueue<Runnable>);
         */
        this.executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    /**
     * 委托，内部使用 ThreadPoolExecutor.execute() 方法实现
     *
     * @param task 任务
     */
    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");

        executor.execute(task);

        System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());   // 线程池中当前线程数目
        /**
         * 下面两个数目在计算期间有可能变化，所以只是返回一个近似值
         */
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount()); // 正在执行的估计线程数目
        System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());   // 估计完成任务数目
    }

    /**
     * 委托，内部使用 ThreadPoolExecutor.shutdown() 实现
     *
     * 1. shutdown() 之后，ThreadPoolExecutor 不再接受新任务
     * 2. 不会 等待之前已经提交的任务完成之后才执行
     */
    public void endServer() {
        executor.shutdown();
    }
}
