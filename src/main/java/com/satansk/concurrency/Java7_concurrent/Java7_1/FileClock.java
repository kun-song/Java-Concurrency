package com.satansk.concurrency.Java7_concurrent.Java7_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    15:11 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class FileClock implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s %d\n", new Date(), i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("FileClock has been interrupted!");
                /**
                 * 1. 此处捕获中断，FileClock 可以自己决定怎么处理中断
                 * 2. 如果没有 return，则只是打印上面的消息，线程依旧运行，如果 return 则中断时结束线程
                 */
//                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();

        /**
         * 1. 休眠 main 线程，5s 之后执行 interrupt() 函数
         * 2. FileClock 线程此时大约打印了 5 条信息
         * 3. 中断 FileClock 任务的执行线程，FileClock 将会捕获到该中断
         */
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }
}