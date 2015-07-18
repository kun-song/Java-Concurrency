package com.main.Java7_concurrent.Java7_7.priorityExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:17 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * 存储在 PriorityBlockingQueue 中的元素必须实现 Comparable 接口
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
    private int priority;
    private String name;

    public MyPriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        System.out.printf("MyPriorityTask: (%s, %d)\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getPriority() {
        return priority;
    }
}
