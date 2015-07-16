package com.main.Java7_concurrent.Java7_3.producerConsumerByExchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Author:  satansk
 * Date:    15:19 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Consumer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Consumer: " + buffer.size());
            for (int j = 0; j < 10; j++) {
                String msg = buffer.get(0);
                System.out.println("Consumer: " + msg);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
