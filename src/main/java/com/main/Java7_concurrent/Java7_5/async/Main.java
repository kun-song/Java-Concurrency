package com.main.Java7_concurrent.Java7_5.async;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    16:52 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor sys = new FolderProcessor("C:\\", "log");
        pool.execute(sys);

        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");

            TimeUnit.SECONDS.sleep(1);
        } while(! sys.isDone());

        pool.shutdown();

        List<String> results = sys.join();
        System.out.printf("C: %d files found.\n", results.size());
    }
}
