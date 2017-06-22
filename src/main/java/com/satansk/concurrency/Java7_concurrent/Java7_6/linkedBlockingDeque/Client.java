package com.satansk.concurrency.Java7_concurrent.Java7_6.linkedBlockingDeque;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    22:13 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Client implements Runnable {
    private LinkedBlockingDeque<String> requestList;

    public Client(LinkedBlockingDeque<String> requestList) {
        this.requestList = requestList;
    }

    @Override
    public void run() {
        System.out.printf("Client: Begin\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder request = new StringBuilder();
                request.append("(");
                request.append(i);
                request.append("--");
                request.append(j);
                request.append(")");
                try {
                    requestList.put(request.toString());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Client: End\n");
    }
}
