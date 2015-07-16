package com.main.Java7_concurrent.Java7_1.daemoThread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    18:28 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class WriterTask implements Runnable {
    private Deque<Event> queue;

    public WriterTask(Deque<Event> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event",
                    Thread.currentThread().getId()));
            queue.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
