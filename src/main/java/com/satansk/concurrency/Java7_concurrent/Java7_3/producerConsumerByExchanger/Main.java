package com.satansk.concurrency.Java7_concurrent.Java7_3.producerConsumerByExchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Author:  satansk
 * Date:    15:22 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(exchanger, buffer1);
        Consumer consumer = new Consumer(exchanger, buffer2);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
