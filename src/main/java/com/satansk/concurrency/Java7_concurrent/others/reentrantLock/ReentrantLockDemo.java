package com.satansk.concurrency.Java7_concurrent.others.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:  satansk
 * Date:    22:28 at 2015/7/21
 * Email:   satansk@hotmail.com
 */
public class ReentrantLockDemo implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    /**
     * 1. ReentrantLock 是可重入锁，同一线程，可以重复获取
     * 2. get() 首先获取了ReentrantLock，然后里面调用 set() 方法，又获取一次 ReentrantLock
     * 3. 调用一次 get() 获取了两次 ReentrantLock
     */
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockDemo lock = new ReentrantLockDemo();

        /**
         * 1. Thread 1 lock()，执行完毕之后 unlock()，然后
         * 2. Thread 2 lock()，执行完毕之后 unlock()，然后
         * 3. Thread 3 lock()，执行完毕之后 unlock()
         */
        new Thread(lock).start();
        new Thread(lock).start();
        new Thread(lock).start();
    }
}
