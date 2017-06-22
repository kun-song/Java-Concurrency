package com.satansk.concurrency.Java7_concurrent.Java7_6.threadLocalRandom;

/**
 * Author:  satansk
 * Date:    16:03 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}
