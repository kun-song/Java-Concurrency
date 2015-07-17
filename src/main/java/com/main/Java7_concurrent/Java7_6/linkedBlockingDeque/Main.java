package com.main.Java7_concurrent.Java7_6.linkedBlockingDeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    22:17 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 1. 固定容量为 3 的 LinkedBlockingDeque
         * 2. 满时，put 将阻塞
         * 3. 空时，take 将阻塞
         */
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);

        Client client = new Client(list);
        new Thread(client).start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                /**
                 * 1. take() 是阻塞方法
                 * 2. 虽然每 300 毫秒 take 一次，但是 LinkedBlockingDeque 可能为空，
                 *      所以实际两次输出间隔可能远大于 300 毫秒
                 */
                String request = list.take();
                System.out.printf("Main: request: %s at %s. Size: %d\n", request, new Date(), list.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }

        System.out.printf("Main: End of Main\n");
    }
}
