package com.satansk.concurrency.Java7_concurrent.Java7_6.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  satansk
 * Date:    20:39 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class PoolTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public PoolTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
