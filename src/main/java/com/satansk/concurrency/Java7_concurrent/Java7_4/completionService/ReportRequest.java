package com.satansk.concurrency.Java7_concurrent.Java7_4.completionService;

import java.util.concurrent.CompletionService;

/**
 * Author:  satansk
 * Date:    19:07 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class ReportRequest implements Runnable {
    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator generator = new ReportGenerator(name, "Report");
        /**
         * 1. 使用 CompletionService 来执行一个任务，需要使用 CompletionService.submit() 方法
         */
        service.submit(generator);
    }
}
