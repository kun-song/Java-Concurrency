package com.satansk.concurrency.Java7_concurrent.Java7_2.producerConsumerByCondition;

/**
 * Author:  satansk
 * Date:    11:41 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Buffer buffer = new com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Buffer(20);

        com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Producer producer = new com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Producer(mock, buffer);
        Thread producerThread = new Thread(producer);

        com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Consumer[] consumers = new com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Consumer[3];
        Thread[] comsumerThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new com.main.Java7_concurrent.Java7_2.producerConsumerByCondition.Consumer(buffer);
            comsumerThreads[i] = new Thread(consumers[i]);
        }

        producerThread.start();
        for (Thread t : comsumerThreads) {
            t.start();
        }
    }
}
