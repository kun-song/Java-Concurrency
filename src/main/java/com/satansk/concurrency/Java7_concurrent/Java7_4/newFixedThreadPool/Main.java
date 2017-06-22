package com.satansk.concurrency.Java7_concurrent.Java7_4.newFixedThreadPool;


/**
 * Author:  satansk
 * Date:    19:24 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * 本例中的 Server 使用固定大小为 5 的线程池来实现，所以运行时可以发现，大部分任务在 Server.executeTask() 中
 * 的输出语句都结束之后，才从阻塞队列中依次被 5 个工作线程执行完毕。
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);

            server.executeTask(task);
        }

        server.endServer();
    }
}
