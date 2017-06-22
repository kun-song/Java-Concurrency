package com.satansk.concurrency.Java7_concurrent.Java7_7.threadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    18:50 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class SleepTwoSecondsTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}
