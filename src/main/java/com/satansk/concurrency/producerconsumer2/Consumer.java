package com.satansk.concurrency.producerconsumer2;

import java.util.concurrent.BlockingQueue;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/23
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

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
