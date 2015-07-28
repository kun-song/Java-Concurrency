package com.main.Java7_concurrent.Java7_2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Song
 * Date:   19:49 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * 1. ReentrantLock 可重入锁，可递归调用。
 *
 * 2. 锁加在何处？
 *
 *      答：临界区，通常是物理上唯一的资源，或者不能同时访问的资源。
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock();

    /**
     * 1. lock 方法与 内在锁 类似，如果无法获取锁，则休眠当前进程
     * 2. tryLock 不会休眠进程，立即返回：
     *      (1) 获取成功，true
     *      (2) 获取失败，false
     *
     * @param document 模拟打印文件
     */
    public void printJob(Object document){
        try {
            queueLock.lock();   // 获取锁
            long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
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
    }
}
