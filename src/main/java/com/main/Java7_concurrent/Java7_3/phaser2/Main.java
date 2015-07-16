package com.main.Java7_concurrent.Java7_3.phaser2;

/**
 * Author:  satansk
 * Date:    14:11 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();

        com.main.Java7_concurrent.Java7_3.phaser2.Student[] students = new com.main.Java7_concurrent.Java7_3.phaser2.Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new com.main.Java7_concurrent.Java7_3.phaser2.Student(phaser);
            /**
             * 1. 在 phaser1 例子中使用 new Phaser(3) 创建的 Phaser，此处我们使用 register，
             *      所以不用显示表明参与者数量
             *
             * 2. 其实参与者数只是一个数字，phaser.register() 不会在 phaser 与参与者之间建立任何关系，
             */
            phaser.register();
        }

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(students[i]);
            threads[i].start();
        }

        /**
         * main 线程等待 5 个线程的终结
         */
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("main thread has finished!");
    }
}
