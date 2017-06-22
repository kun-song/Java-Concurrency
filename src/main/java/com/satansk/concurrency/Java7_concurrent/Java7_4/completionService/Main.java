package com.satansk.concurrency.Java7_concurrent.Java7_4.completionService;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:14 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 *  ReportGenerator 产生模拟的 report 数据
 *
 *  1. ReportRequest 中使用 CompletionService.submit() 来提交 ReportGenerator 任务。
 *  2. ReportProcessor 中使用 CompletionService.poll() 获取 ReportGenerator 的 Future 对象（执行结果）
 *
 *  CompletionService 需要使用一个 Executor 初始化，并用这个 Executor 来执行任务，CompletionService 比
 *  Executor 增加的功能是在将
 *      1. 提交任务
 *      2. 获取结果
 *  两个阶段分在不同对象中。
 *
 *  CompletionService:
 *      1. poll()
 *          (1) 无等待时间：若队列为空，立刻返回 null
 *          (2) 有等待时间：等待
 *      2. take()
 *          (1) 不带参数
 *          (2) 若队列为空，则阻塞直到队列中有元素
 *      3. poll & take 只要获取到了队列中的 Future，就立刻将其从队列中删除
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        /**
         * 1. 使用 Executor 来初始化 ExecutorCompletionService 对象
         * 2. 因为 CompletionService 内部是使用 Executor 来执行任务的
         */
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        ReportRequest faceRequest = new ReportRequest("Face", service);
        ReportRequest onLineRequest = new ReportRequest("onLine", service);
        Thread faceT = new Thread(faceRequest);
        Thread onLineT = new Thread(onLineRequest);

        ReportProcessor processor = new ReportProcessor(service);
        Thread senderT = new Thread(processor);

        // 启动 3 个线程
        System.out.printf("Main: Starting the Threads\n");
        faceT.start();
        onLineT.start();
        senderT.start();

        // 等待 faceT onLineT 结束
        System.out.printf("Main: Waiting for the report generators.\n");
        faceT.join();
        onLineT.join();

        System.out.printf("Main: shutting down the executor.\n");
        executor.shutdown();

        /**
         * 1. Executor.shutdown() 之后，已经执行的任务还在继续
         * 2. Executor.awaitTermination() 会阻塞直到所有的任务执行结束
         */
        executor.awaitTermination(1, TimeUnit.DAYS);

        processor.setEnd(true);
        System.out.println("Main: ends");
    }
}
