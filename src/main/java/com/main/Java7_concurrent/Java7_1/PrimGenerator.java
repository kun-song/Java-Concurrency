package com.main.Java7_concurrent.Java7_1;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:05 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class PrimGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrim(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            /**
             * 1. 线程自行检查是否被中断，然后做出中断响应
             * 2. 也可以不对中断响应，直接无视
             * 3. 这里，检测到中断直接 return
             */
            if (isInterrupted()) {
                System.out.println("interrupted");
                return;
            }
            number++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread task = new PrimGenerator();
        task.start();

        TimeUnit.SECONDS.sleep(2);
        /**
         * 1. 中断一个线程，就在该线程上调用 interrupted() 方法
         * 2. 如果线程有中断响应机制，则可以响应该中断，如果没有，则无效
         */
        task.interrupt();
    }

    private boolean isPrim(long n) {
        if (n <= 2)
            return true;
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
