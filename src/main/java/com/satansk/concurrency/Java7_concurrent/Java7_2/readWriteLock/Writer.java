package com.satansk.concurrency.Java7_concurrent.Java7_2.readWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   20:38 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Writer implements Runnable {
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Writer begin to modify the prices");
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.println("Writer finish modifying the prices");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("Writer has been interrupted");
            }
        }
    }
}
