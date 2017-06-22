package com.satansk.concurrency.Java7_concurrent.Java7_5.recursiveTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Author:  satansk
 * Date:    14:13 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * DocumentTask 统计 word 在整个文件（二位数组）中出现的次数，内部使用 LineTask 实现。
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private String[][] document;
    private int start;
    private int end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;

        if (end - start < 10) {
            result = processLine(document, start, end, word);
        } else {
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            /**
             * 这里只有两个 sub-task，所以不用 invokeAll(Collection c)
             */
            invokeAll();

            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 将对 Document(String[][]) 的处理转化成对其中行(String[])的处理:
     *  1. 对于每一行，使用 LineTask 来处理
     *  2. 然后合并所有 LineTask 的计算结果
     */
    private int processLine(String[][] document, int start, int end, String word) {
        int result = 0;

        List<LineTask> tasks = new ArrayList<>();
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        /**
         * 执行 tasks 列表中的所有任务
         */
        invokeAll(tasks);

        /**
         * 合并列表中任务的结果
         */
        for (int i = 0; i < tasks.size(); i++) {
            try {
                result += tasks.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 合并计算结果
    private int groupResults(int n1, int n2) {
        return n1 + n2;
    }
}
