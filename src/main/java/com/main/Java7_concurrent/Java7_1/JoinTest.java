package com.main.Java7_concurrent.Java7_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    16:28 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread thread_1 = new Thread(new DataSourcesLoader());
        Thread thread_2 = new Thread(new NetworkConnectionsLoader());

        thread_1.start();
        thread_2.start();

        /**
         * 1. 如果没有下面的 join()，则 main 线程直接执行完毕，输出 end main，然后才输出 thread_1 thread_2 的输出
         * 2. join 之后，main 线程会休眠，等待 thread_1 thread_2 两个线程结束之后重新开始执行
         */
        try {
            thread_1.join();
            thread_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end main");
    }

    private static class DataSourcesLoader implements Runnable {

        @Override
        public void run() {
            System.out.printf("begin data loading: %s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("end data loading: %s\n", new Date());
        }
    }

    private static class NetworkConnectionsLoader implements Runnable {
        @Override
        public void run() {
            System.out.printf("begin network loading: %s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("end network loading: %s\n", new Date());
        }
    }
}