package com.satansk.concurrency.Java7_concurrent.Java7_2.readWriteLock;

/**
 * Author: Song
 * Date:   20:36 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Reader implements Runnable {
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
