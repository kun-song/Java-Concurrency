package com.main.Java7_concurrent.Java7_7.runnableScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    7:13 at 2015/7/19
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    @Override
    public void run() {
        System.out.printf("Task: Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task: End.\n");
    }
}
