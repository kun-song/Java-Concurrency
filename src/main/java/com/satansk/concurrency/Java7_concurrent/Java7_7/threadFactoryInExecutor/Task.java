package com.satansk.concurrency.Java7_concurrent.Java7_7.threadFactoryInExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    20:00 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            System.out.printf(Thread.currentThread().toString());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
