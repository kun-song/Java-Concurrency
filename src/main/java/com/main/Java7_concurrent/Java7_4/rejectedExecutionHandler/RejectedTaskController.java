package com.main.Java7_concurrent.Java7_4.rejectedExecutionHandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author:  satansk
 * Date:    20:07 at 2015/7/16
 * Email:   satansk@hotmail.com
 *
 * RejectedExecutionHandler 在无法向 Executor 提交任务时调用：
 *
 *  1. 队列满
 *  2. Executor.shutdown() 之后
 */
public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: The task %s has been rejected\n", r.toString());
        System.out.printf("RejectedTaskController: %s\n", executor.toString());
        System.out.printf("RejectedTaskController: Terminating: %s\n", executor.isTerminating());
        System.out.printf("RejectedTaskController: Terminated: %s\n", executor.isTerminated());
    }
}
