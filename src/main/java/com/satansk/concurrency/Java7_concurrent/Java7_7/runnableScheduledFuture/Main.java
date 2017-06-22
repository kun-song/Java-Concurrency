package com.satansk.concurrency.Java7_concurrent.Java7_7.runnableScheduledFuture;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    7:14 at 2015/7/19
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
        Task task = new Task();
        System.out.printf("Main: %s\n",new Date());

        executor.schedule(task, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(3);

        task = new Task();
        System.out.printf("Main: %s\n",new Date());

        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.printf("Main: End of the program.\n");
    }
}
