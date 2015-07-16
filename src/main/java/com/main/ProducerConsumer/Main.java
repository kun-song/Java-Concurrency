package com.main.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    14:48 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> items = new ArrayBlockingQueue<>(1024);
        Executor executor = Executors.newFixedThreadPool(2);

        Producer producer = new Producer(items);
        Consumer consumer = new Consumer(items);

        executor.execute(producer);
        executor.execute(consumer);
    }
}
