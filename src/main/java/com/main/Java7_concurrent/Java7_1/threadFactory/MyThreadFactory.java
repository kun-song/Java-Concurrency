package com.main.Java7_concurrent.Java7_1.threadFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Author: Song
 * Date:   15:48 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;    // 线程数量
    private String name;    // 工厂名字
    private List<String> stats; // 统计

    public MyThreadFactory(String name) {
        this.name = name;
        counter = 0;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format("created thread %d with %s on %s\n",
                thread.getId(),
                thread.getName(),
                new Date()));
        return thread;
    }

    public String getStats() {
        StringBuilder sb = new StringBuilder();
        for (String s : stats) {
            sb.append(s);
        }

        return sb.toString();
    }
}
