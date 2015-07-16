package com.main.Java7_concurrent.Java7_4.future.cancel;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    10:26 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true) {
            System.out.println("Task: running every 10 milliseconds");
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
