package com.satansk.concurrency.Java7_concurrent.Java7_6.delayedQueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * Author:  satansk
 * Date:    9:36 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();

        Date delay = new Date();
        delay.setTime(now.getTime() + id * 1000);
        System.out.printf("Thread-%s: %s\n", id, delay);

        // 添加 100 个具有相同激活日期的 Event 元素
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
