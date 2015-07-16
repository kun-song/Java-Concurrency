package com.main.Java7_concurrent.Java7_1;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    17:23 at 2015/7/10
 * Email:   satansk@hotmail.com
 */
public class FileSearch implements Runnable {
    private String fileName;    // 要搜索的文件
    private String initPath;    // 搜索路径

    public FileSearch(String fileName, String initPath) {
        this.fileName = fileName;
        this.initPath = initPath;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s : search has been interrupted\n",
                        Thread.currentThread().getName());
            }
        }
    }

    /**
     * 递归处理目录
     *
     * @param file 目录
     * @throws InterruptedException 若被中断，则抛出异常
     */
    private void directoryProcess(File file) throws InterruptedException {
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

        /**
         * 1. Thread.interrupted() 查看当前线程是否被中断
         * 2. 清理中断标志位
         */
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    /**
     * 文件处理，如果该文件与要搜索的文件同名，则输出文件名字
     *
     * @param f 文件
     * @throws InterruptedException 若处理过程中线程被中断，抛出异常
     */
    private void fileProcess(File f) throws InterruptedException {
        if (f.getName().equals(fileName)) {
            System.out.printf("%s : %s\n",
                    Thread.currentThread().getName(),
                    f.getAbsolutePath());
        }

        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FileSearch searcher = new FileSearch("log.txt", "C:\\");
        Thread searchThread = new Thread(searcher);
        searchThread.start();

        TimeUnit.SECONDS.sleep(1);
        /**
         * 1. Thread.interrupt 中断当前线程
         * 2. 与 Thread.interrupted() 区别(检测中断)
         */
        searchThread.interrupt();
    }
}