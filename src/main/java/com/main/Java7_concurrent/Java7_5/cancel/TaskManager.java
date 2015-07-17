package com.main.Java7_concurrent.Java7_5.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * Author:  satansk
 * Date:    19:05 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * Fork/Join 框架无法取消所有任务，使用该工具类实现 取消所有。
 */
public class TaskManager {
    private List<ForkJoinTask<Integer>> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task) {
        tasks.add(task);
    }

    /**
     * 当有任务完成时调用，将取消该完成任务以外的所有其他任务
     *
     * @param cancelTask 已经完成的 ForkJoinTask，取消所有其他任务
     */
    public void cancelAllTasks(ForkJoinTask<Integer> cancelTask) {
        for (ForkJoinTask<Integer> task : tasks) {
            if (task != cancelTask) {
                /**
                 * 1. ForkJoinTask.cancel() 取消 未开始执行 的任务
                 * 2. 若该任务已经结束，或者由于其他原因(比如正在执行)无法取消，return false
                 * 3. 无法取消正在执行的任务，所以最后可能有多个任务都找到 number
                 */
                task.cancel(true);
                ((SearchNumberTask) task).writeCancelMessage();
            }
        }
    }
}
