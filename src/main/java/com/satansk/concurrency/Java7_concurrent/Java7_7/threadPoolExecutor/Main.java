package com.satansk.concurrency.Java7_concurrent.Java7_7.threadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    18:51 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyExecutor executor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
        List<Future<String>> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            Future<String> result = executor.submit(task);
            resultList.add(result);
        }

        // 前 5 个任务
        for (int i = 0; i < 5; i++) {
            String result = resultList.get(i).get();
            System.out.printf("Main: result for task-%d is %s\n", i, result);
        }

        executor.shutdown();

        // 后 5 个任务
        for (int i = 5; i < 10; i++) {
            String result = resultList.get(i).get();
            System.out.printf("Main: result for task-%d is %s\n", i, result);
        }
        executor.awaitTermination(1, TimeUnit.DAYS);

        /**
         * 1. shutdown() 之后还有在队列中待处理的任务，下面输出来看，shutdown() 之后队列中的任务也会被执行
         * 2. 只是不能提交新任务了
         */
        System.out.printf("Main: total completed task = %d\n", executor.getCompletedTaskCount());

        System.out.printf("Main: end\n");
    }
}
