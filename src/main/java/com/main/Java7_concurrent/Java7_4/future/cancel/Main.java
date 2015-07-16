package com.main.Java7_concurrent.Java7_4.future.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    10:27 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * Future.cancel()
 *
 *  1. 失败
 *      (1) 任务已经结束
 *      (2) 任务已经取消过
 *
 *  2. 成功
 *      (1) 若任务 还没有启动，则其永远不会被启动
 *      (2) 已经启动：true 可中断  false 不可中断
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();
        Future<String> future = executor.submit(task);

        // 休眠 500 毫秒 之后取消任务，该任务未取消之前会不断输出 Task: running every 10 milliseconds
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Main: canceling the Task");
        /**
         * 1. 任务已经在执行
         * 2. true: 中断任务    false: 不中断任务
         * 3. 能够取消任务的关键是，任务中是否有中断响应代码
         */
        future.cancel(true);

        System.out.printf("Main: canceled: %s\n", future.isCancelled());
        System.out.printf("Main: done: %s\n", future.isDone());
    }
}
