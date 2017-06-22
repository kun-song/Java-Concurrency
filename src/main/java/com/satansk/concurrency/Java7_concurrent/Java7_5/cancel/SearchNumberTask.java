package com.satansk.concurrency.Java7_concurrent.Java7_5.cancel;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    19:09 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class SearchNumberTask extends RecursiveTask<Integer> {
    private int[] numbers;
    private int start, end;
    private int number;
    private TaskManager taskManager;
    private static final int NOT_FOUND = -1;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager taskManager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.taskManager = taskManager;
    }

    @Override
    protected Integer compute() {
        System.out.println("compute Task(" + start + " -> " + end + ")");

        int ret;
        if (end - start > 10) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }

        return ret;
    }

    /**
     * 任务过大，分而治之
     *
     * @return Number 出现的位置; NOT_FOUND;
     */
    private int launchTasks() {
        int mid = (start + end) / 2;

        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, taskManager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid, end, number, taskManager);
        /**
         * 将新建任务添加到 TaskManager 中，用于以后 取消所有 操作
         */
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        task1.fork();
        task2.fork();

        int resultValue;

        resultValue = task1.join();
        if (resultValue != NOT_FOUND) {
            return resultValue;
        }

        resultValue = task2.join();
        if (resultValue != -1) {
            return resultValue;
        }

        return NOT_FOUND;
    }

    /**
     * @return  i: number 出现的位置
     *          NOT_FOUND: 未找到
     */
    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: Number %d has been found at position %d\n", number, i);
                // 取消所有其余任务
                taskManager.cancelAllTasks(this);
                return i;   // 找到
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return NOT_FOUND;    // 未找到
    }

    /**
     * 任务取消，输出信息
     */
    public void writeCancelMessage() {
        System.out.printf("Task(%d -> %d) has been canceled\n", start, end);
    }
}
