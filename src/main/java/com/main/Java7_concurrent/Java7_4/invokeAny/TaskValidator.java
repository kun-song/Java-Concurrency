package com.main.Java7_concurrent.Java7_4.invokeAny;

import java.util.concurrent.Callable;

/**
 * Author:  satansk
 * Date:    22:24 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class TaskValidator implements Callable<String> {
    private UserValidator validator;
    private String user;
    private String password;

    public TaskValidator(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        /**
         * validator.validate() 等待一段时间（睡眠模拟验证过程）
         */
        if (! validator.validate(user, password)) {
            System.out.printf("%s: The user has not been found\n", validator.getName());
            throw new Exception("Error validating user");
        } else {
            System.out.printf("%s: The user has been found\n", validator.getName());
            return validator.getName();
        }
    }
}
