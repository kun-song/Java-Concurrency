package com.main.Java7_concurrent.Java7_4.invokeAny;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    22:18 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public boolean validate(String name, String password) {
        Random random = new Random();

        // 随机等待一段时间，模拟验证
        long duration = (long) (Math.random() * 10);
        try {
            System.out.printf("Validator %s: validating a user during %d seconds.\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        // 返回一个随机 boolean， true: 通过  false: 不通过
        return random.nextBoolean();
    }

    public String getName() {
        return name;
    }
}
