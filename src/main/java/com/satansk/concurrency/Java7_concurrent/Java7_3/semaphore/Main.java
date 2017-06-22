package com.satansk.concurrency.Java7_concurrent.Java7_3.semaphore;

/**
 * Author:  satansk
 * Date:    15:41 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new com.main.Java7_concurrent.Java7_3.semaphore.Job(printQueue), "Thread " + i);
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
