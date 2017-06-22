package com.satansk.concurrency.Java7_concurrent.Java7_6.delayedQueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    9:40 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * DelayQueue:
 *  1. 存储的元素必须实现 Delayed 接口
 *  2. 未到激活日期，则无法取出元素，未到期元素相当于对 poll() 等操作透明
 *  3. size() 方法不受激活日期影响
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Event> queue = new DelayQueue<>();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Task(i + 1, queue));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        /**
         * 1. 轮询方式，查看 DelayQueue 中的元素
         * 2. 前面的线程使用 join() 方法，所以这里 DelayQueue 中已经具有 5 * 100 个元素，但是他们激活时间，
         *      只有到激活时间，poll() 才能取出元素
         * 3. 虽然没到激活时间，但是 DelayQueue.size() 还是实际大小，只是不能取出元素。
         */
        do {
            int counter = 0;
            Event event;
            do {
                event = queue.poll();   // poll() 非阻塞方法，若空 return null
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            System.out.printf("At %s you have read %d events\n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}
