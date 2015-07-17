package com.main.Java7_concurrent.Java7_6.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Author:  satansk
 * Date:    20:41 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];

        // 添加
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched!\n", threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.printf("Main: size of the List: %d\n", list.size());

        // 删除
        for (int i = 0; i < threads.length; i++) {
            PoolTask task = new PoolTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PoolTask have been launched\n", threads.length);
        for (Thread t : threads) {
            t.join();
        }
        /**
         * 1. ConcurrentLinkedDeque 是一个异步集合，多线程修改，所以 size() 计算其容量需要遍历其每个元素，
         *      因此，size() 是 non-constant 时间
         * 2. size() 方法执行时，可能有其他线程修改了集合，所以 size() 返回的是一个估计值。
         * 3. 由于其不准确性，并发中较少用
         */
        System.out.printf("Main: size of the List: %d\n", list.size());
    }
}
