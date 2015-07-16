package com.main.Java7_concurrent.Java7_4.rejectedExecutionHandler;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    20:09 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // 开始
        System.out.println("Task " + name + ": Starting");

        // 随机休眠
        try {
            long duration=(long)(Math.random()*10);
            System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds.\n",
                    name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 结束
        System.out.printf("Task %s: Ending\n",name);
    }
}
