package com.satansk.concurrency.Java7_concurrent.Java7_2.readWriteLock;

/**
 * Author: Song
 * Date:   20:41 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        com.main.Java7_concurrent.Java7_2.readWriteLock.Reader[] readers = new com.main.Java7_concurrent.Java7_2.readWriteLock.Reader[5];
        Thread[] readerThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new com.main.Java7_concurrent.Java7_2.readWriteLock.Reader(pricesInfo);
            readerThreads[i] = new Thread(readers[i]);
        }

        com.main.Java7_concurrent.Java7_2.readWriteLock.Writer writer = new com.main.Java7_concurrent.Java7_2.readWriteLock.Writer(pricesInfo);
        Thread writerThread = new Thread(writer);

        // TODO 此处，输出是交叉进行，与教程不符！
        writerThread.start();
        for (Thread t : readerThreads) {
            t.start();
        }
    }
}
