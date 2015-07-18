package com.main.Java7_concurrent.Java7_7.threadFactoryInExecutor;

import java.util.concurrent.ThreadFactory;

/**
 * Author:  satansk
 * Date:    19:58 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        this.counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread thread = new MyThread(r, prefix + "-" + counter);
        counter++;
        return thread;
    }
}
