package com.satansk.concurrency.Java7_concurrent.Java7_3.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Author:  satansk
 * Date:    17:42 at 2015/7/14
 * Email:   satansk@hotmail.com
 *
 * 使用 CountDownLatch 来模拟实现会议，会议需要等待 10 个参与者都到齐之后才进行。
 *
 * 1. 首先，会议处于等待状态，所以第一调用 CountDownLatch.await() 阻塞本线程
 * 2. Participant 线程，调用 arrive() 方法，从而减少正在等待的线程数目
 * 3. 等到 等待线程 = 0 时，就唤醒本线程，开始会议
 */
public class VideoConference implements Runnable {
    private CountDownLatch controller;

    /**
     * 初始化会议
     *
     * @param number 会议成员数目，也就是本线程需要等待的线程数目
     */
    public VideoConference(int number) {
        this.controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        try {
            /**
             * 1. 本线程一直阻塞
             * 2. 直到所有 10 个参与者到齐
             */
            controller.await();
            System.out.println("All participants has come, let's begin!");
        } catch (InterruptedException e) {
            System.out.println("VideoConference has been interrupted!");
        }
    }

    /**
     * 1. 内部调用 CountDownLatch.countDown() 来减少等待的线程数目
     * 2. 当减到 0 时，本线程被唤醒执行
     *
     * @param name 参与者名字
     */
    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();
        System.out.printf("waiting for %d\n", controller.getCount());
    }
}
