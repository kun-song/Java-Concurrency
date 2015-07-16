package com.main.Java7_concurrent.Java7_4.future1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    21:11 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number == 0 || number == 1) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: factorial %d's result = %d\n",Thread.currentThread().getName(),
                number, result);

        return result;
    }
}
