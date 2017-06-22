package com.satansk.concurrency.Java7_concurrent.Java7_3.semaphore2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:  satansk
 * Date:    15:34 at 2015/7/14
 * Email:   satansk@hotmail.com
 *
 * Semaphore 可以控制多个线程对共享资源同时访问
 *
 */
public class PrintQueue {
    private boolean[] freePrinters;
    // 保护 freePrinters 的访问
    private Lock lockPrinters;

    private final Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    public void printJob() {
        try {
            // 获取 Semaphores
            semaphore.acquire();

            int assignedPrinter = getPrinter();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job int Printer %d during %d seconds\n",
                    Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            System.out.println("printJob(Object document) has been interrupted!");
        } finally {
            // 释放 Semaphores
            semaphore.release();
        }
    }

    /**
     * 对于 freePrinters 的访问，使用 ReentrantLock 进行保护
     *
     * @return 0 1 2 成功获取  -1 获取失败
     */
    public int getPrinter() {
        int ret = -1;
        try {
            lockPrinters.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }

        } finally {
            lockPrinters.unlock();
        }
        return ret;
    }
}