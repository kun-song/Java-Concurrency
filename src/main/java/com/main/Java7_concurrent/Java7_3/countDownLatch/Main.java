package com.main.Java7_concurrent.Java7_3.countDownLatch;

/**
 * Author:  satansk
 * Date:    17:49 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        new Thread(conference).start();

        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(conference, "Thread " + i);
            new Thread(participant).start();
        }
    }
}
