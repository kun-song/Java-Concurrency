package com.satansk.concurrency.Java7_concurrent.Java7_4.future1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    21:18 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * Callable + Future 构成了可返回结果的任务，使用 Executor 并发执行。
 *
 * Future:
 *      1. 控制任务：
 *          (1) 取消任务
 *          (2) 查看任务是否完成
 *
 *      2. 获取任务结果：
 *          (1) 阻塞方法，如果 Callable 未完成，则等待
 *          (2) 直到任务完成、中断、超时
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();

        // 生成 0-9 之间的 10 的随机数
        Random random = new Random();
        for (int i =0; i < 10; i++) {
            Integer number = random.nextInt(10);

            FactorialCalculator calculator = new FactorialCalculator(number);
            futureList.add(executor.submit(calculator));
        }

        /**
         * 等待所有 Callable 任务完成
         */
        do {
            System.out.printf("Main: number of completed tasks: %d\n", executor.getCompletedTaskCount());
            // 输出每个 Callable 任务的完成情况
            for (int i = 0; i < futureList.size(); i++) {
                /**
                 * Future.isDone() 查看任务是否完成
                 */
                System.out.printf("Main: task %d isDone ?: %s\n", i, futureList.get(i).isDone());
            }
            TimeUnit.MILLISECONDS.sleep(50);
        } while (executor.getCompletedTaskCount() < futureList.size());

        /**
         * 输出所有 Callable 任务的执行结果
         */
        for (int i = 0; i < futureList.size(); i++) {
            /**
             * Future.get() 是阻塞方法
             */
            Future<Integer> resultFuture = futureList.get(i);

            Integer resultInteger = resultFuture.get();

            System.out.printf("Main: Task %d: %d\n", i, resultInteger);
        }
    }
}
