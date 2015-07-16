package com.main.ProducerConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Author:  satansk
 * Date:    15:01 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("consumes " + queue.take());
            System.out.println("consumes " + queue.take());
            System.out.println("consumes " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
