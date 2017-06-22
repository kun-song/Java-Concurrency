package com.satansk.concurrency.Java7_concurrent.Java7_3.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Author:  satansk
 * Date:    19:33 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) {
        final int ROW = 10000;
        final int NUMBER = 1000;
        final int SEARCH = 6;
        final int TARGET = 5;
        final int LINES_PARTICIPANT = 2000;

        MatrixMock matrixMock = new MatrixMock(ROW, NUMBER, SEARCH);
        Result result = new Result(ROW);
        Grouper grouper = new Grouper(result);
        /**
         * CyclicBarrier 等待 5 个线程，然后执行 grouper
         */
        CyclicBarrier barrier = new CyclicBarrier(TARGET, grouper);

        Searcher[] searchers = new Searcher[TARGET];
        for (int i = 0; i < TARGET; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    matrixMock, result, 5, barrier);
            new Thread(searchers[i]).start();
        }
    }
}
