package com.main.Java7_concurrent.Java7_4.invokeAny;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  satansk
 * Date:    22:27 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        String userName = "test";
        String password = "test";

        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");

        TaskValidator ldapTask = new TaskValidator(ldapValidator, userName, password);
        TaskValidator dbTask = new TaskValidator(dbValidator, userName, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);

        ExecutorService executor = Executors.newCachedThreadPool();
        String result;

        try {
            /**
             * 1. invokeAny() 接受任务数列，并启动它们
             * 2. 返回完成时 (1) 没有抛出异常的 (2) 第一个任务的 结果
             * 3. invokeAny() 返回值与 Callable 任务中 call() 的返回值一致
             */
            result = executor.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Main: end of main thread!");
    }
}
