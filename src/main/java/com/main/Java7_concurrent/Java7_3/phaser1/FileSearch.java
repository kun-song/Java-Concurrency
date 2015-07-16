package com.main.Java7_concurrent.Java7_3.phaser1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    8:19 at 2015/7/15
 * Email:   satansk@hotmail.com
 *
 * 功能：在指定文件夹以及子文件夹搜索： 1. 确定扩展名  2. 24小时内修改的 文件
 *
 * Phaser: 以 arriveAndAwaitAdvance() 方法为界限，将任务分为三个阶段，线程在每个阶段的结尾进行同步。
 *
 *      1. run() 开头 phaser.arriveAndAwaitAdvance();
 *      2. resultsIsEmpty() 非空时
 *      3. showInfo() 结尾
 */
public class FileSearch implements Runnable {
    private String initPath;    // 初始搜索路径
    private String tag; // 扩展名
    private List<String> results;   // 符合条件的文件的路径
    private Phaser phaser;

    public FileSearch(String initPath, String tag, Phaser phaser) {
        this.initPath = initPath;
        this.tag = tag;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }

    @Override
    public void run() {
        /**
         * 1. arriveAndAwaitAdvance() 减少 actual phase 的线程数
         * 2. 并让该线程进入休眠，直到全部其他线程结束 phase
         * 3. 在 run() 开头调用 arriveAndAwaitAdvance() 方法，则没有任何 FileSearch 线程可以开始工作，
         *      直到全部线程被创建。
         */
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        } else {
            System.out.printf("%s: %s is not a directory.\n", Thread.currentThread().getName(), initPath);
        }

        if (resultsIsEmpty()) {
            return;
        }

        filterResults();

        if (resultsIsEmpty()) {
            return;
        }

        showInfo();
        /**
         * 取消注册，表明线程已经结束了 actual phase，不再继续 phased 的操作了
         */
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
    }

    /**
     * 递归处理文件夹，遇到文件，使用 fileProcess() 进行处理
     *
     * @param file
     */
    private void directoryProcess(File file) {
        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    directoryProcess(f);
                } else {
                    fileProcess(f);
                }
            }
        }
    }

    /**
     * 处理文件，验证扩展名是否是 tag，如果是，将其绝对路径加入 results 列表
     *
     * @param file 文件名
     */
    private void fileProcess(File file) {
        if (file.getName().endsWith(tag)) {
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * 过滤在第一阶段获得的文件列表，删除修改超过 24 小时的文件。
     */
    private void filterResults() {
        List<String> newResults = new ArrayList<>();    // 暂存路径
        long actualDate = new Date().getTime(); // 当前时间

        for (String path : results) {
            File file = new File(path);
            long fileDate = file.lastModified();

            /**
             * 如果修改时间在 24 小时之内，则将该路径加入 newResults
             */
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(path);
            }
        }

        results = newResults;
    }

    /**
     * 在第一和第二个 phase 的结尾调用，检查结果是否为空。
     *
     * @return  true: 空
     *          false: 非空
     */
    private boolean resultsIsEmpty() {
        if (results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase());
            /**
             * 1. 通知线程已经结束 actual phase，并且离开 phased 操作
             * 2. 减少了一个参与线程
             */
            phaser.arriveAndDeregister();
            return true;
        } else {
            System.out.printf("%s: Phase %d: %d results.\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase(),
                    results.size());
            /**
             * 通知 actual phase，它将被阻塞直到 phased 操作的全部参与线程结束 actual phase
             */
            phaser.arriveAndAwaitAdvance();
            return false;
        }
    }

    /**
     * 打印 results 内容
     */
    private void showInfo() {
        System.out.println();
        for (String path : results) {
            File file = new File(path);
            System.out.printf("%s: Phase %d: %s\n",
                    Thread.currentThread().getName(),
                    phaser.getPhase(),
                    file.getAbsolutePath());
        }
        System.out.println();
        phaser.arriveAndAwaitAdvance();
    }
}
