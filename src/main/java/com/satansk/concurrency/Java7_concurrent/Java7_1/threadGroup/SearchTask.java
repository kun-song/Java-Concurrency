package com.satansk.concurrency.Java7_concurrent.Java7_1.threadGroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   14:20 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s: Start\n", name);
        try {
            doTask();
        } catch (InterruptedException e) {
            /**
             * 检测到中断立即 return
             */
            System.out.printf("Thread %s has been interrupted\n", name);
            return;
        }
        /**
         * 任务完成者线程的 name ，作为 result 的内容
         */
        result.setName(name);
        System.out.printf("Thread %s: End\n", name);
    }

    /**
     * 随机随眠一段时间
     *
     * @throws InterruptedException
     */
    private void doTask() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread %s: sleep %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        for (int i = 0; i < 10; i++) {
            new Thread(threadGroup, searchTask).start();
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.printf("Active number of threads: %d\n", threadGroup.activeCount());
        System.out.println("Information about the Thread Group:");
        /**
         * 打印线程组中线程信息
         */
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        /**
         * 将线程组中的活跃线程复制到 threads 数组中
         */
        threadGroup.enumerate(threads);
        System.out.println("\nThreads state in threadGroup: ");
        for (Thread t : threads) {
            System.out.printf("Thread %s: %s\n", t.getName(), t.getState());
        }

        waitFinish(threadGroup);
        /**
         * 只要有一个线程完成，即中断其他所有线程
         */
        threadGroup.interrupt();
    }

    /**
     * 等待线程组中有一个线程完成工作
     *
     * @param threadGroup
     * @throws InterruptedException
     */
    private static void waitFinish(ThreadGroup threadGroup) throws InterruptedException {
        /**
         * 1. 线程组中本来有 10 个活跃线程，当有一个执行结束，就只有 9 个活跃线程了，根据这个来判断是否有线程完成
         * 2. 每秒检测一次是否有线程完成工作
         * 3. sleep 时间(随机生成)最少的线程最先完成
         */
        while (threadGroup.activeCount() > 9) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
