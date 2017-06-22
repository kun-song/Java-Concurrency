package com.satansk.concurrency.Java7_concurrent.Java7_3.countDownLatch;

import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:46 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Participant implements Runnable {
    private VideoConference conference;
    private String name;

    public Participant(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("Participant has been interrupted!");
        }
        /**
         * 1. arrive() 内部调用 CountDownLatch.countDown()
         */
        conference.arrive(name);
    }
}
