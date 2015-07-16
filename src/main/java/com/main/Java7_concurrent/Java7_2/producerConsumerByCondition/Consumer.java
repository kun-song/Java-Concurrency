package com.main.Java7_concurrent.Java7_2.producerConsumerByCondition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    11:39 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        Random random = new Random();
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            System.out.println("processLine() has been interrupted!");
        }
    }
}
