package com.satansk.concurrency.thread;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/14
 */
public class HelloRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from runnable!");
    }

    public static void main(String[] args) {
        (new Thread(new HelloRunnable())).start();
    }
}
