package com.main.Java7_concurrent.Java7_6.atomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Author:  satansk
 * Date:    17:16 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);
        Thread[] inThreads = new Thread[100];
        Thread[] deThreads = new Thread[100];

        Incrementer incrementer = new Incrementer(vector);
        Decrementer decrementer = new Decrementer(vector);

        for (int i = 0; i < 100; i++) {
            inThreads[i] = new Thread(incrementer);
            deThreads[i] = new Thread(decrementer);

            inThreads[i].start();
            deThreads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            deThreads[i].join();
            inThreads[i].join();
        }

        for (int i = 0; i < vector.length(); i++) {
            if (vector.get(i) != 0) {
                System.out.printf("Vector[%d] = %d\n", i, vector.get(i));
            }
        }

        System.out.println("Main: end of main.");
    }
}
