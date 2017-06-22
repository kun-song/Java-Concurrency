package com.satansk.concurrency.Java7_concurrent.Java7_2.producerConsumer;

/**
 * Author: Song
 * Date:   19:06 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Producer implements Runnable {
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
