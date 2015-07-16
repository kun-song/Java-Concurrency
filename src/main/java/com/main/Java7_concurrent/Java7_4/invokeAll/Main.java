package com.main.Java7_concurrent.Java7_4.invokeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author:  satansk
 * Date:    23:42 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * ExecutorService.invokeAll():
 *      1. 所有任务结束之后
 *      2. 返回全部任务的 Future (所以是个 List)
 *
 * ExecutorService.invokeAny():
 *      1. 一个任务成功结束就返回
 *      2. 返回成功任务的 Future
 *
 * Executes the given tasks, returning a list of Futures holding
 * their status and results when all complete.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        // 任务列表
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Task task = new Task("" + i);
            taskList.add(task);
        }

        /**
         * 1. 将任务列表提交到 Executor 中，获取全部任务的执行结果
         * 2. invokeAll()
         *      (1) 接受 Callable 集合作为参数
         *      (2) 返回一个 List<Future<T>>
         */
        List<Future<Result>> futureList = executor.invokeAll(taskList);

        executor.shutdown();

        // 打印 List<Future<Result>> 的内容
        System.out.println("Main: Printing the results");
        for (int i = 0; i < futureList.size(); i++){
            Future<Result> future = futureList.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName() + ": " + result.getValue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
