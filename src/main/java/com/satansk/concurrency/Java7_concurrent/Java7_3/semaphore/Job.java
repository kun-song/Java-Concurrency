package com.satansk.concurrency.Java7_concurrent.Java7_3.semaphore;

/**
 * Author:  satansk
 * Date:    15:39 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: print has been finished!\n", Thread.currentThread().getName());
    }
}