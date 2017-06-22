package com.satansk.concurrency.thread;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/14
 */
public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (String message : importantInfo) {
            // 暂停当前线程 4s（也就是 main 线程），不释放监视器锁
            Thread.sleep(4000);

            System.out.println(message);
        }
    }
}
