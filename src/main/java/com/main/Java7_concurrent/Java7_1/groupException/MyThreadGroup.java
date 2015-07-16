package com.main.Java7_concurrent.Java7_1.groupException;

/**
 * Author: Song
 * Date:   15:18 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    /**
     * 当：
     *      1. 本线程组中线程抛出 unchecked exception
     *      2. 该线程没有设置 {@link java.lang.Thread.UncaughtExceptionHandler}
     *
     * 时，JVM 将使用本方法处理线程的 unchecked exception
     *
     * @param thread 抛出 unchecked exception 的线程
     * @param exception 抛出的异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        System.out.printf("The thread %s has thrown an Exception\n", thread.getId());
        exception.printStackTrace(System.out);
        System.out.println("Terminating the rest of the Threads");
        /**
         * 中断线程组中所有线程
         */
        interrupt();
    }
}
