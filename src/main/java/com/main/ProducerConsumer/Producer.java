package com.main.ProducerConsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    14:51 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class Producer implements Runnable {
    private BlockingQueue<String> queue = null;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
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
