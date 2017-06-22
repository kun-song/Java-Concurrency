package com.satansk.concurrency.producerconsumer2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/23
 */
public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> items = new ArrayBlockingQueue<String>(1024);

        Executor executor = Executors.newFixedThreadPool(2);

        Producer producer = new Producer(items);
        Consumer consumer = new Consumer(items);

        executor.execute(producer);
        executor.execute(consumer);
    }
}
