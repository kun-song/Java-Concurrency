package com.main.Java7_concurrent.Java7_6.priorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Author:  satansk
 * Date:    22:56 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * 1. 5 个线程并发向队列添加元素，PriorityBlockingQueue 按照优先级设置其插入位置。
 * 2. 如果是普通队列，则无法保证插入顺序（因为 5 个线程是并发执行的）
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i, queue);
            threads[i] = new Thread(task);
        }

        // 启动前 5 个线程
        for (Thread t : threads) {
            t.start();
        }

        // 等待这 5 个线程完成
        for (Thread t : threads) {
            t.join();
        }

        System.out.printf("Main: queue size = %d\n", queue.size());

        for (int i = 0; i < threads.length * 1000; i++) {
            /**
             * 1. take() 阻塞方法，将阻塞直到队列中有元素
             * 2. poll() 非阻塞方法，队列为空，return null
             */
            Event event = queue.poll();
            if (event != null) {
                System.out.printf("Thread %s: Priority %d\n", Thread.currentThread().getName(), event.getPriority());
            }
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }
}
