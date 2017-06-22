package com.satansk.concurrency.Java7_concurrent.Java7_6.priorityBlockingQueue;

/**
 * Author:  satansk
 * Date:    22:48 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * 存储在 PriorityBlockingQueue 中的元素必须实现 Comparable 接口
 */
public class Event implements Comparable<Event> {
    private int thread; // 以创建事件的线程 ID
    private int priority;   // 事件的优先级

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    /**
     * 通常实现 Comparable 接口时：
     *  1. this < para  return -1
     *  2. this > para  return 1
     *  3. this = para  return 0
     *
     *  本例的实现与通常做法相反
     */
    @Override
    public int compareTo(Event o) {
        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }
}
