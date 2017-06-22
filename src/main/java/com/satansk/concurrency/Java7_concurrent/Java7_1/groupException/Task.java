package com.satansk.concurrency.Java7_concurrent.Java7_1.groupException;

import java.util.Random;

/**
 * Author: Song
 * Date:   15:26 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * 线程中出现 UncheckedException 时的处理步骤：
 *
 *  1. 寻找该线程的 UncaughtExceptionHandler，若无
 *  2. 寻找该线程所属线程组的 UncaughtExceptionHandler，若无
 *  3. 寻找 default UncaughtExceptionHandler，若无
 *  4. 打印 stacktrace，结束
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 / (int) (random.nextDouble() * 1000);
            System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);

            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("%d : interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyThreadGroup myThreadGroup = new MyThreadGroup("myThreadGroup");
        Task task = new Task();

        for (int i = 0; i < 2; i++) {
            new Thread(myThreadGroup, task).start();
        }
    }
}
