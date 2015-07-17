package com.main.Java7_concurrent.Java7_5.exception;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:33 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Task extends RecursiveTask<Integer> {
    private int[] array;
    private int start, end;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Task: Start --> from %s to %d\n", start, end);
        if (end - start < 10) {
            if (start < 3 && 3 < end) {
                /**
                 * 1. ForkJoinTask 中抛出 unchecked exception
                 * 2. 但是任务依然进行，不会结束，就好像没有抛出异常一样，
                 *      需要 Main 中调用 isCompletedAbnormally() 来检查是否抛出异常
                 */
                throw new RuntimeException("Exception: from " + start + " to " + end);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            int mid = (start + end) / 2;
            Task task1 = new Task(array, start, mid);
            Task task2 = new Task(array, mid, end);

            invokeAll(task1, task2);
        }

        System.out.printf("Task: End --> from %d to %d\n", start, end);


        return 0;
    }
}
