package com.satansk.concurrency.Java7_concurrent.Java7_1.threadFactory;

import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   15:54 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            /**
             * 1. 每个线程的 ID 不同，证明创建的是不同的线程
             */
            System.out.printf("threadId: %d\n", Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("MyFactory");
        Task task = new Task();

        /**
         * 1. thread 只是一个线程引用，每次引用不同的线程，作用只在于启动线程任务
         * 2. 每次对 thread 赋值之后，thread 引用失效，无法找回以前引用的对象
         */
        Thread thread;
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
            /**
             * 1. 等待所有 10 个线程创建并且执行完 run() 之后才输出 factory 的统计信息
             * 2. 如果没有 join()，则 main 线程和 10 个线程并发执行
             */
            thread.join();
        }

        System.out.printf("stats: %s\n", factory.getStats());
    }
}
