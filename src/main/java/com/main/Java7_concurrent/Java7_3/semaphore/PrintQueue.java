package com.main.Java7_concurrent.Java7_3.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    15:34 at 2015/7/14
 * Email:   satansk@hotmail.com
 *
 * 1. 启动 10 个线程调用 printJob()，第一个获取 Semaphores，然后其他的线程都会被阻塞，直到第一个释放 Semaphores
 *      这样，10 个打印任务一个接一个一次进行。
 *
 * 2. 类似 Lock & Synchronized 关键字
 *
 * 3. Semaphores 的 acquire 方法：
 *
 *      1. acquireUninterruptibly(): 如果 Semaphores 计数器为 0，会忽略线程中断，不抛出任何异常
 *      2. tryAcquire(): 如果获取失败，不会阻塞线程，直接返回 false
 *
 * 4. 可以指定 Semaphores 的公平性
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        /**
         * Binary Semaphore
         */
        this.semaphore = new Semaphore(1);
    }

    public void printJob(Object document) {
        try {
            // 获取 Semaphores
            semaphore.acquire();

            /**
             * 随机休眠，模拟打印
             */
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: printing a job duration %d seconds\n",
                    Thread.currentThread().getName(), duration);
            TimeUnit.MILLISECONDS.sleep(duration);

        } catch (InterruptedException e) {
            System.out.println("printJob(Object document) has been interrupted!");
        } finally {
            // 释放 Semaphores
            semaphore.release();
        }
    }
}