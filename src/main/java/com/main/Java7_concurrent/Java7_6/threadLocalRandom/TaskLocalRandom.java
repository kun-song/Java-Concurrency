package com.main.Java7_concurrent.Java7_6.threadLocalRandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Author:  satansk
 * Date:    16:00 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * ThreadLocalRandom
 *  1. 独立于当前线程的 随机数生成器
 *  2. 在并发应用中，相对多线程共享的 Random，线程独立的 ThreadLocalRandom 具有更小的开销、更少的竞争
 *  3. 并行线程中尤为适用
 *  4. 典型用法: ThreadLocalRandom.current().nextInt()，这样使用时，多线程之间永远不会意外共享 ThreadLocalRandom 对象
 */
public class TaskLocalRandom implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            /**
             * 1. ThreadLocalRandom.current() --> 返回属于当前线程的 ThreadLocalRandom
             * 2. current() 方法保证永远不发生线程意外共享 ThreadLocalRandom
             */
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }
}
