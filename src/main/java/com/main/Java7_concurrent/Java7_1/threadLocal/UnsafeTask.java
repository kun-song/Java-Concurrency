package com.main.Java7_concurrent.Java7_1.threadLocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   13:57 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class UnsafeTask implements Runnable {
    private Date startDate;
    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting thread: %s, at %s\n", Thread.currentThread().getId(), startDate);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Stopping thread: %s, at %s\n", Thread.currentThread().getId(), startDate);
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 1. 三个线程有不同的开始时间
         * 2. 结束时间相同，因为三个线程共享 startDate 变量，最后一个启动的线程修改 startDate 之后，
         *      三个线程结束时都使用了最后一个线程修改的 startDate。
         */
        UnsafeTask unsafeTask = new UnsafeTask();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(unsafeTask);
            thread.start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
