package com.satansk.concurrency.Java7_concurrent.Java7_2.fairLock;

import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   19:55 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: Finish print\n", Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (Thread t : threads) {
            t.start();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
