package com.main.Java7_concurrent.Java7_4.completionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:10 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class ReportProcessor implements Runnable {
    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while (! end) {
            try {
                /**
                 * 1. CompletionService 开始一个任务后，将该任务的 Future 对象存储在一个队列中（该任务处于待完成状态）
                 * 2. poll() 方法获取该队列中第一个 Future 元素（该 Future 控制的任务已经完成），然后在
                 *      队列中删除该 Future 元素
                 * 3. poll() 中可以设置等待时间，防止 poll 时队列为空
                 */
                Future<String> future = service.poll(20, TimeUnit.SECONDS);
                if (future != null) {
                    /**
                     * Future.get() 获取该 Future 对象控制的 Callable 任务的计算结果
                     */
                    String report = future.get();
                    System.out.printf("ReportReceiver: Report Received:%s\n", report);
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("ReportSender: End\n");
        }
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
