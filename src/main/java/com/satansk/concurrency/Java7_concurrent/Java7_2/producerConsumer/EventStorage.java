package com.satansk.concurrency.Java7_concurrent.Java7_2.producerConsumer;

import java.util.Date;
import java.util.LinkedList;

/**
 * Author: Song
 * Date:   18:44 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * 1. set() get() 中使用 while() 而不是 if() 进行判断，这是因为线程存在着 休眠-唤醒 这个循环状态，需要在每次唤醒的
 * 时候进行 storage 容量判断。
 *
 * 2. 如果有多个线程 set() 多个线程 get()，每次只有一个线程能够 set()，每次也只有一个线程能够 get()。其他大部分线程
 * 一直在 休眠-唤醒 这个循环中反反复复。
 *
 * 3. while 被称为自旋锁。
 *
 * 4. wait() 释放锁，然后 notify() 之后，唤醒的线程重新获取锁，然后从 wait() 返回，继续 while() 判断
 * 5. notify() 不释放锁，需要执行完 notify 之后的代码之后才释放锁
 */
public class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    /**
     * 1. 首先检查 storage 是否已满，满，则调用 wait()，直到 storage 有空的空间
     * 2. 在 set() 末尾，添加元素完成之后，调用 notifyAll() 唤醒所有在 wait() 方法上休眠的线程
     */
    public synchronized void set() {
        /**
         * 1. 此处必须使用 while() 不能用 if()
         * 2. 当 storage 满时，线程 A 执行 set() 将会进入 while，从而执行 wait()，阻塞线程 A，当其他线程调用
         *      get() 时，将会调用 notifyAll() 从而唤醒所有阻塞线程，假设 A 首先获取内在锁，则 A 从 wait() 方法返回，
         *      继续 while() 进行条件判断，此时 storage 非满，则 A 成功 offer，之后 storage 又满，下一个获取
         *      内在锁的线程又执行 wait()，从阻塞所有调用 set() 方法的线程，直到其他线程继续 get()
         */
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException in set()");
            }
        }
        storage.offer(new Date());
        System.out.printf("Set(): %d\n", storage.size());
        /**
         * 唤醒等待的 get() 线程
         */
        notifyAll();
    }

    /**
     * 1. 判断 storage 是否为空，空则 wait()，释放锁，休眠本线程
     * 2. 非空，则取出头元素，notifyAll() 唤醒在 wait() 上休眠的线程
     */
    public synchronized void get() {
        /**
         * 1. storage 为空时，若线程 A 调用 get()，则线程 A 执行 wait()，然后释放内在锁，其他线程能够再次获取该锁
         * 2. 线程 A 等待其他线程执行 notifyAll() 之后，线程 A 被唤醒，从 wait() 方法返回，再次判断 storage 是否为空，
         *      此时，storage 必不为空，从而可以执行 poll()
         * 3. poll() 之后，storage 又变成空了，所有调用 get() 的方法又被阻塞
         */
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException in get()");
            }
        }

        System.out.printf("Get(): %d: %s\n", storage.size(), storage.poll());
        /**
         * 唤醒等待的 set() 线程
         */
        notifyAll();
    }
}
