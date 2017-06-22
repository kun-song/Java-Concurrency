package com.satansk.concurrency.Java7_concurrent.Java7_4.newFixedThreadPool;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:03 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private Date initDate;
    private String name;

    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());

        // 模拟工作
        try {
            Long duration=(long)(Math.random()*10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n",
                    Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
    }
}
