package com.satansk.concurrency.Java7_concurrent.Java7_7.threadFactory;

/**
 * Author:  satansk
 * Date:    20:00 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();
        Thread thread = factory.newThread(task);

        thread.start();
        thread.join();

        System.out.printf("Main: Thread information:\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End.\n");
    }
}
