package com.main.Java7_concurrent.Java7_3.phaser1;

import java.util.concurrent.Phaser;

/**
 * Author:  satansk
 * Date:    8:55 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        FileSearch windows = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch java = new FileSearch("C:\\Java", "log", phaser);
        FileSearch android = new FileSearch("C:\\Android", "log", phaser);

        Thread windowsThread = new Thread(windows, "Windows");
        Thread javaThread = new Thread(java, "Java");
        Thread androidThread = new Thread(android, "Android");
        windowsThread.start();
        javaThread.start();
        androidThread.start();

        windowsThread.join();
        javaThread.join();
        androidThread.join();

        /**
         * Phaser 有 0 个参与者的时候，就处于 terminated 状态
         */
        System.out.println("Terminated: " + phaser.isTerminated());
    }
}
