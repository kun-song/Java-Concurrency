package com.satansk.concurrency.Java7_concurrent.Java7_1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:  satansk
 * Date:    16:34 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class Calculator_2 implements Runnable {
    private int number;

    public Calculator_2(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d\n",
                    Thread.currentThread().getName(),
                    number,
                    i,
                    i * number);
        }
    }

    public static void main(String[] args) throws IOException {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator_2(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("thread " + i);
        }

        PrintWriter pw = new PrintWriter(new FileWriter("log.txt"));
        for (int i = 0; i < 10; i++) {
            pw.println("Main: Status of thread " + i + " " + threads[i].getState());
        }

        for (Thread t : threads) {
            t.start();
        }

        boolean finish = false;
        while (! finish) {
            for (int i = 0; i < 10; i++) {
                if (threads[i].getState() != states[i]) {
                    writeThreadInfo(pw, threads[i], states[i]);
                    states[i] = threads[i].getState();
                }
            }

            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main: Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main: Priority %d - %s\n", thread.getPriority(), thread.getName());
        pw.printf("Main: old state %s\n", state);
        pw.printf("Main: new state %s\n", thread.getState());
        pw.printf("Main: ***********************\n");
    }
}
