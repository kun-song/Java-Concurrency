package com.satansk.concurrency.Java7_concurrent.Java7_7.threadFactoryInExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    20:17 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        /**
         * 1. 使用自定义的 ThreadFactory 来创建 Executor
         * 2. Executor 中的线程池将使用 MyThreadFactory 来创建
         */
        ExecutorService executor = Executors.newCachedThreadPool(factory);

        Task task = new Task();
        executor.submit(task);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Main: end!");
    }
}
