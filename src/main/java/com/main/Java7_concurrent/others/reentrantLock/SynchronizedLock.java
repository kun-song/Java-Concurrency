package com.main.Java7_concurrent.others.reentrantLock;

/**
 * Author:  satansk
 * Date:    22:21 at 2015/7/21
 * Email:   satansk@hotmail.com
 */
public class SynchronizedLock implements Runnable {

    /**
     * 1. synchronized 是可重入锁，同一线程，可以重复获取
     * 2. get() 首先获取了内在锁，然后里面调用 set() 方法，又获取一次内在锁
     * 3. 调用一次 get() 获取了两次 intrinsic lock
     */
    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynchronizedLock lock = new SynchronizedLock();

        /**
         * 1. Thread 1 获取 lock 对象的内在锁，执行完毕之后释放，然后
         * 2. Thread 2 获取 lock 对象的内在锁，执行完毕之后释放，然后
         * 3. Thread 3 获取 lock 对象的内在锁，执行完毕之后释放
         */
        new Thread(lock).start();
        new Thread(lock).start();
        new Thread(lock).start();
    }
}
