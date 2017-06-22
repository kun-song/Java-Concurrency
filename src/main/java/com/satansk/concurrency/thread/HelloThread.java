package com.satansk.concurrency.thread;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/14
 */
public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from Thread!");
    }

    public static void main(String[] args) {
        (new HelloThread()).start();
    }
}
