package com.main.Java7_concurrent.Java7_6.priorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Author:  satansk
 * Date:    22:53 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(id, i); // 优先级递增
            queue.add(event);
        }
    }
}
