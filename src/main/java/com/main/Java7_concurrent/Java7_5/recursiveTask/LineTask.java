package com.main.Java7_concurrent.Java7_5.recursiveTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    15:41 at 2015/7/17
 * Email:   satansk@hotmail.com
 *
 * LineTask 统计单词在一行中出现的次数
 */
public class LineTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    private String[] line;
    private int start, end;
    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (end - start < 100) {    // 分治界限
            result = count(line, start, end, word);
        } else {
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid, word);
            LineTask task2 = new LineTask(line, mid, end, word);
            invokeAll();

            try {
                /**
                 * 1. RecursiveTask.get() 阻塞直到任务完成
                 * 2. 等待子任务完成时，工作线程可以 steal 其他任务执行
                 */
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 查找一行中的 word 出现次数
     */
    private int count(String[] line, int start, int end, String word) {
        int counter = 0;
        for (int i = start; i < end; i++) {
            if (line[i].equals(word)) {
                counter++;
            }
        }

        // 休眠 10 毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return counter;
    }

    /**
     * 合并计算结果
     */
    private int groupResults(int n1, int n2) {
        return n1 + n2;
    }
}
