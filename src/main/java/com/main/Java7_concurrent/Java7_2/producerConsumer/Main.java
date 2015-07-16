package com.main.Java7_concurrent.Java7_2.producerConsumer;

/**
 * Author: Song
 * Date:   19:08 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();
    }
}
