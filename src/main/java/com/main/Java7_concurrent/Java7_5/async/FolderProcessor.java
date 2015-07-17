package com.main.Java7_concurrent.Java7_5.async;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Author:  satansk
 * Date:    16:37 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * Fork/Join 一般配合使用：
 *  1. 提交子任务时用 subTask.fork()
 *  2. 获取子任务结果时用 subTask.join()
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = 1L;

    private String path;    // 全路径
    private String extension;   // 扩展名

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcessor> taskList = new ArrayList<>();

        File file = new File(path);
        File[] content = file.listFiles();

        if (content != null) {  // != null 表明 path 是目录
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    /**
                     * 1. fork() 异步执行
                     */
                    task.fork();
                    taskList.add(task);
                } else {
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }

            if (taskList.size() > 50) {
                System.out.printf("%s: %d tasks running\n", file.getAbsolutePath(), taskList.size());
            }
        }
        /**
         * 1. 将子任务列表的计算结果添加到 list 中
         * 2. 如果不添加，则 list 中只有第一个任务的结果，3 / 606
         * 3. 没有返回值，直接修改了参数 list
         */
        addResultsFromTasks(list, taskList);
        return list;
    }

    /**
     * 等待子任务执行完毕，合并子任务的计算结果
     */
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor task : tasks) {
            /**
             * 1. join(): return the results of the computation when it is done.
             * 2. join() 将等待子任务完成，并获取其结果
             */
            list.addAll(task.join());
        }
    }

    /**
     * 检查文件的扩展名是否是正在查找的
     */
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
