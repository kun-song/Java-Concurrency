package com.satansk.concurrency.Java7_concurrent.Java7_4.newFixedThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author:  satansk
 * Date:    19:20 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * 固定大小的线程池：
 *      1. 开始时，有新任务就创建一个新线程来执行，直到到达基本线程容量
 *      2. TODO 然后怎么执行来着 ？
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        /**
         * new ThreadPoolExecutor(
         *      nThreads,   // 基本容量
         *      nThreads,   // 最大容量
         *      0L,
         *      TimeUnit.MILLISECONDS,
         *      new LinkedBlockingQueue<Runnable>());
         *
         * 1. 固定大小的线程池，基本容量 = 最大容量 = 5
         * 2. 当任务数量超过 5 时，任务将被阻塞，直到有空闲线程来执行它
         */
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
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

        System.out.printf("Server: Task Count: %d\n", executor.getTaskCount());
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
