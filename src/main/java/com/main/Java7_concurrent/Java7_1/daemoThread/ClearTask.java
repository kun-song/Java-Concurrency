package com.main.Java7_concurrent.Java7_1.daemoThread;

import java.util.Date;
import java.util.Deque;

/**
 * Author:  satansk
 * Date:    18:46 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class ClearTask extends Thread {
    private Deque<Event> queue;

    public ClearTask(Deque<Event> queue) {
        this.queue = queue;
        /**
         * 设置为守护线程
         */
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (queue.size() == 0) {
            return;
        }
        delete = false;

        do {
            Event e = queue.getLast();
            difference = date.getTime() - e.getDate().getTime();

            if (difference > 10000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                queue.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if (delete) {
            System.out.printf("Cleanser: size of the queue: %d\n", queue.size());
        }
    }
}
