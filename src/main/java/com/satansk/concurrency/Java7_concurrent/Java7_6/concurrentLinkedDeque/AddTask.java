package com.satansk.concurrency.Java7_concurrent.Java7_6.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  satansk
 * Date:    20:37 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class AddTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.add(Thread.currentThread().getName() + "-" + i);
        }
    }
}
