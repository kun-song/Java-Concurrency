package com.satansk.concurrency.Java7_concurrent.Java7_4.completionService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:04 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * 1. ReportGenerator 生成模拟的报告数据，实际只有:  name + ": " + title
 * 2. 随机休眠，模拟不同生成速度
 */
public class ReportGenerator implements Callable<String> {
    private String sender;
    private String title;

    public ReportGenerator(String title, String sender) {
        this.title = title;
        this.sender = sender;
    }

    @Override
    public String call() throws Exception {
        long duration = (long) (Math.random() * 10);
        TimeUnit.SECONDS.sleep(duration);
        System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n",
                this.sender, this.title, duration);

        return sender + ": " + title;
    }
}
