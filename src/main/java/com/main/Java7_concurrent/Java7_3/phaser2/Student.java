package com.main.Java7_concurrent.Java7_3.phaser2;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    13:59 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * onAdvance() 在 阶段改变之前 与 唤醒 arriveAndAwaitAdvance() 之前调用。本例中调用了 4 次。
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s: has arrived to don the exam.\n", Thread.currentThread().getName());
        /**
         * 1. 等待其他学生入场：
         *      （1）所有线程执行 run() 方法，到此处的 arriveAndAwaitAdvance() 等待
         *      （2）等全部线程都到达此处后，所有线程才能向下个阶段进行
         */
        phaser.arriveAndAwaitAdvance(); // phase 0

        /*********************** exercise 1 **************************/
        System.out.printf("%s: Is going to do the first exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        doExercise1();
        System.out.printf("%s: Has done the first exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        /**
         * 2. 等待其他结束 exercise 1，然后所有线程才能进入 exercise 2 阶段
         */
        phaser.arriveAndAwaitAdvance(); // phase 1

        /************************ exercise 2 **************************/
        System.out.printf("%s: Is going to do the second exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        doExercise2();
        System.out.printf("%s: Has done the second exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        /**
         * 3. 等待其他结束 exercise 2，所有学生同时开始 exercise 3
         */
        phaser.arriveAndAwaitAdvance(); // phase 2

        /************************ exercise 2 ***************************/
        System.out.printf("%s: Is going to do the third exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        doExercise3();
        System.out.printf("%s: Has done the third exercise. %s\n",
                Thread.currentThread().getName(),
                new Date());
        /**
         * 4. 等待其他结束 exercise 3，然后所有线程才能进入 结束 阶段 -- 不允许提交交卷
         */
        phaser.arriveAndAwaitAdvance(); // phase 3
    }

    private void doExercise3() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void doExercise2() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void doExercise1() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
