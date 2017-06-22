package com.satansk.concurrency.Java7_concurrent.Java7_1.uncheckedException;

/**
 * Author: Song
 * Date:   13:17 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Task implements Runnable {
    @Override
    public void run() {
        Integer.parseInt("ss");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
