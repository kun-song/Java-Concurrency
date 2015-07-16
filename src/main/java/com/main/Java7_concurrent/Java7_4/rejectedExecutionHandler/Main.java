package com.main.Java7_concurrent.Java7_4.rejectedExecutionHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author:  satansk
 * Date:    20:11 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * 1. A handler for tasks that cannot be executed by a {@link ThreadPoolExecutor}.
 * 2. RejectedExecutionHandler 将处理 Executor.shutdown() 之后再提交的任务
 *
 * 当 Executor 接受任务时，它会检查 shutdown() 是否被调用，如果被调用，则拒绝该任务。然后：
 *
 *  1. 查找 setRejectedExecutionHandler 设置的 Handler
 *  2. 若没有设置，则抛出 RejectedExecutionException
 */
public class Main {
    public static void main(String[] args) {
        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        /**
         * 1. 为 Executor 设置 RejectedExecutionHandler
         * 2. 如果没有设置，则 shutdown() 之后提交任务，将会抛出 RejectedExecutionException
         */
        executor.setRejectedExecutionHandler(controller);

        System.out.printf("Main: Starting.\n");
        for (int i = 0; i < 3; i++) {
            Task task=new Task("Task-" + i);
            executor.submit(task);
        }

        System.out.printf("Main: Shutting down the Executor.\n");
        executor.shutdown();

        /**
         * 1. shutdown() 之后 Executor 将不再接受新任务
         * 2. 再次提交的任务将由 RejectedExecutionHandler 来处理
         */
        System.out.printf("Main: Sending another Task.\n");
        executor.submit(new Task("RejectedTask"));

        System.out.println("Main: End");
    }
}
