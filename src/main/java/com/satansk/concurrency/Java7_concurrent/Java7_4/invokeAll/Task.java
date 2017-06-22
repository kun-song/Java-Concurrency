package com.satansk.concurrency.Java7_concurrent.Java7_4.invokeAll;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    23:39 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Task implements Callable<Result> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.printf("%s: Staring\n", this.name);

        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5 个随机数的和作为 result 的值
        int value = 0;
        for (int i = 0; i < 5; i++){
            value += (int) (Math.random() * 100);
        }

        Result result = new Result();
        result.setName(this.name);  // task name
        result.setValue(value);

        System.out.println(this.name + ": Ends");
        return result;
    }
}
