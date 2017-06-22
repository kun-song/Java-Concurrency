package com.satansk.concurrency.Java7_concurrent.Java7_4.newScheduledThreadPool_delay;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Author:  satansk
 * Date:    8:49 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s: starting at : %s\n", name, new Date());
        return "Song Kun";
    }
}
