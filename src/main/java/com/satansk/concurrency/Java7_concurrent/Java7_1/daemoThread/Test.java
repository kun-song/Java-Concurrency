package com.satansk.concurrency.Java7_concurrent.Java7_1.daemoThread;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Author:  satansk
 * Date:    18:58 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class Test {
    public static void main(String[] args) {
        Deque<Event> eventDeque = new ArrayDeque<>();

        com.main.Java7_concurrent.Java7_1.daemoThread.WriterTask writerTask = new com.main.Java7_concurrent.Java7_1.daemoThread.WriterTask(eventDeque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }

        ClearTask clearTask = new ClearTask(eventDeque);
        clearTask.start();
    }
}
