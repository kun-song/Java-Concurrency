package com.main.Java7_concurrent.Java7_2.producerConsumer;

/**
 * Author: Song
 * Date:   19:07 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
