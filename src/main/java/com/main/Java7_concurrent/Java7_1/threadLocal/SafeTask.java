package com.main.Java7_concurrent.Java7_1.threadLocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   14:07 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class SafeTask implements Runnable {
    /**
     * 1. ThreadLocal 变量，从属于每个 Thread
     * 2. 这样三个线程都有自己独立的 startDate，互不干扰
     */
    private ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };
    @Override
    public void run() {
        System.out.printf("Starting thread: %s, at %s\n", Thread.currentThread().getId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Stopping thread: %s, at %s\n", Thread.currentThread().getId(), startDate.get());
    }

    public static void main(String[] args) throws InterruptedException {
        SafeTask safeTask = new SafeTask();
        for (int i = 0; i < 3; i++) {
            new Thread(safeTask).start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
