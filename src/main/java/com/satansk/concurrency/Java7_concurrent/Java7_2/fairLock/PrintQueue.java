package com.satansk.concurrency.Java7_concurrent.Java7_2.fairLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Song
 * Date:   21:38 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * ReentrantLock 可以传入一个 boolean 值来决定是否启动 公平锁。
 *
 * 1. false 默认。  线程 1 执行完 输出1 之后
 *      (1) 可能继续执行 输出2
 *      (2) 也可能执行其他线程的 输出1
 *      这里本线程的 输出2 与 其他线程处于同等位置，一起竞争锁
 *
 * 2. true 公平锁。  线程 1 执行完 输出1 之后，从众多等待线程中选取等待时间最长的线程来取得锁，从而执行
 *      (1) 也就是按照 0-9 的顺序来选择线程，执行 输出1
 *      (2) 等待所有线程的 输出1 都结束之后，然后从等待线程中选择等待时间最长的执行，其实就是按照 0-9 依次执行 输出2
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock(true);

    /**
     * @param document 模拟打印文件
     */
    public void printJob(Object document){
        try {
            queueLock.lock();
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    ": PrintQueue: Printing first Job during " + (duration / 1000) + " seconds");
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("printJob has been interrupted!");
        } finally {
            /**
             * 1. unlock 放在 finally 中，保证即使 try 中发生异常，Lock 也能及时释放。
             * 2. 发生异常时，理应释放锁。
             */
            queueLock.unlock();
        }

        try {
            queueLock.lock();
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    ": PrintQueue: Printing second Job during " + (duration / 1000) + " seconds");
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("printJob has been interrupted!");
        } finally {
            queueLock.unlock();
        }
    }
}
