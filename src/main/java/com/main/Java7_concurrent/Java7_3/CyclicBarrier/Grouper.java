package com.main.Java7_concurrent.Java7_3.CyclicBarrier;

/**
 * Author:  satansk
 * Date:    19:32 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: processing result...\n");
        int[] data = result.getData();
        for (int n : data) {
            finalResult += n;
        }
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
