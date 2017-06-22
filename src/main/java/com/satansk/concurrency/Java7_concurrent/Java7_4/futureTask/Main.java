package com.satansk.concurrency.Java7_concurrent.Java7_4.futureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    11:43 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * 1. Future 可以控制 Callable 任务的执行
 * 2. FutureTask 实现了 Future 接口，也可以控制 Callable 任务的执行
 * 3. MyFutureTask 继承了 FutureTask，也可以用来控制 Callable 任务的执行
 *
 * 4. submit(FutureTask task) --> 将 FutureTask 提交给 Executor 执行，而 FutureTask 又控制了 Task 的执行
 *      Executor 间接控制了 Callable 任务
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyFutureTask[] futureTasks = new MyFutureTask[5];

        // 创建 5 个任务，执行 FutureTask
        for (int i = 0; i < 5; i++) {
            Task task = new Task("Task " + i);
            /**
             * 使用 task 创建 MyFutureTask，实现了使用 MyFutureTask 来控制 task 任务
             */
            futureTasks[i] = new MyFutureTask(task);
            /**
             * 1. 注意此处提交给 Executor 的是 FutureTask 对象，FutureTask 闲了 RunnableFuture
             * 2. 既是 Runnable 有是 Future，自然可以提交给 submit() 方法
             */
            executor.submit(futureTasks[i]);
        }

        /**
         * 1. 休眠 5s，取消所有任务
         * 2. 因为此时，部分任务已经完成，不能取消，只能取消未完成的、正在进行中的任务
         */
        TimeUnit.SECONDS.sleep(5);
        for (int i = 0; i < futureTasks.length; i++) {
            futureTasks[i].cancel(true);
        }

        /**
         * 1. 将上一步骤中，已经完成的、未被取消的任务结果输出
         */
        for (int i = 0; i < futureTasks.length; i++) {
            if (! futureTasks[i].isCancelled()) {
                System.out.printf("%s\n", futureTasks[i].get());
            }
        }
        // 调用已经 cancel 任务的 Future.get() 将会抛出 CancellationException
//            System.out.printf("%s\n", futureTasks[i].get());
        executor.shutdown();
    }
}
