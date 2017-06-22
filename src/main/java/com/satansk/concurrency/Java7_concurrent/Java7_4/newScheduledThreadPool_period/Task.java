package com.satansk.concurrency.Java7_concurrent.Java7_4.newScheduledThreadPool_period;

import java.util.Date;

/**
 * Author:  satansk
 * Date:    9:37 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n",name,new Date());
    }
}
