package com.main.Java7_concurrent.Java7_5.forkJoinPool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    9:52 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * ForkJoinPool 主要用来解决分治问题。与普通 Executor 不同的是，ForkJoinPool 采用 work-stealing 算法。
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        Task task = new Task(products, 1, products.size(), 0.2);
        /**
         * 1. ForkJoinPool 池中运行 ForkJoinTask(RecursiveTask, RecursiveAction)
         * 2. 默认配置：创建一个 线程数 = 计算机 CPU 数 的池
         * 3. 当 ForkJoinPool 创建时，这些线程将被创建并且在池中等待，直到有任务到达。
         */
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        /**
         * 1. getActiveThreadCount(): 正在偷取任务、执行任务的线程估计数目(活跃线程)
         * 2. getStealCount():
         * 3. getParallelism(): 并行级别
         */
        do {
            System.out.printf("Main: thread count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: thread steal: %d\n", pool.getStealCount());
            System.out.printf("Main: parallelism: %d\n", pool.getParallelism());
            TimeUnit.MILLISECONDS.sleep(5);
        } while (! task.isDone());

        pool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.println("Main: task has been completed normally.");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Error:  %s: %f\n",product.getName(),product.getPrice());
            }
        }

        System.out.println("Main: End of the program.\n");
    }
}
