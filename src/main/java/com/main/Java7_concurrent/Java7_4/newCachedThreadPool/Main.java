package com.main.Java7_concurrent.Java7_4.newCachedThreadPool;

/**
 * Author:  satansk
 * Date:    19:24 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);

            server.executeTask(task);
        }

        /**
         * 使用 Executor 必须显式关闭，否则程序不会结束
         */
        server.endServer();
    }
}
