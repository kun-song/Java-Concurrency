package com.satansk.concurrency.Java7_concurrent.Java7_3.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Author:  satansk
 * Date:    19:24 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Searcher implements Runnable {
    // 确定将要使用的子集的行
    private int firstRow;
    private int lastRow;

    private MatrixMock mock;
    private com.main.Java7_concurrent.Java7_3.CyclicBarrier.Result result;

    private int target; // 要查找的数字

    private CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow,
                    MatrixMock mock, com.main.Java7_concurrent.Java7_3.CyclicBarrier.Result result,
                    int target, CyclicBarrier barrier) {

        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.result = result;
        this.target = target;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter;    // target 在每行出现的次数
        System.out.printf("%s: processing lines from %d to %d.\n",
                Thread.currentThread().getName(),
                firstRow,
                lastRow);

        for (int i = firstRow; i < lastRow; i++) {
            int[] row = mock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == target) {
                    counter++;
                }
            }
            result.setData(i, counter);
            System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            /**
             * 1. 当其他线程调用 reset() 之后，其他线程抛出 BrokenBarrierException
             */
            e.printStackTrace();
        }
    }
}
