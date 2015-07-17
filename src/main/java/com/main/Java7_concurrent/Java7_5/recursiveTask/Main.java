package com.main.Java7_concurrent.Java7_5.recursiveTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    16:01 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * Main 只执行了一个 DocumentTask，但其实 DocumentTask 会创建很多个子任务来执行，实际 task 可能非常多
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Document generator = new Document();
        // 100 行，每行 1000 单词
        String[][] document = generator.generateDocument(100, 1000, "the");

        DocumentTask task = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        // 定时监控 ForkJoinPool 的变化
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: active threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: steal count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");

            TimeUnit.SECONDS.sleep(1);
        } while (! task.isDone());

        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.DAYS);

        System.out.printf("Main: the Word appears %d times.", task.get());
    }
}
