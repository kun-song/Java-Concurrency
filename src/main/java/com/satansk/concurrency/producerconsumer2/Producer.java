package com.satansk.concurrency.producerconsumer2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/23
 */
public class Producer implements Runnable {
    private BlockingQueue<String> queue = null;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("1");
            TimeUnit.SECONDS.sleep(2);
            queue.put("2");
            TimeUnit.SECONDS.sleep(2);
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
