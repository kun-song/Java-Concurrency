package com.satansk.concurrency.Java7_concurrent.Java7_3.producerConsumerByExchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Author:  satansk
 * Date:    15:12 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Producer implements Runnable {
    private List<String> buffer;    // 交换的数据类型
    private final Exchanger<List<String>> exchanger;    // 同步 Producer & Consumer

    public Producer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }


    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n", cycle);
            // 每次循环，加 10 个字符串到 buffer
            for (int j = 0; j < 10; j++) {
                String msg = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", msg);
                buffer.add(msg);
            }

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Producer: " + buffer.size());
            cycle++;
        }
    }
}
