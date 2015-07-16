package com.main.Java7_concurrent.Java7_3.CyclicBarrier;

import java.util.Random;

/**
 * Author:  satansk
 * Date:    19:14 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class MatrixMock {
    private int[][] data;

    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }
    }

    /**
     * 获取数组的一行
     *
     * @param row 行数
     * @return 行作为数组返回，不存在则返回 null
     */
    public int[] getRow(int row) {
        if (row >= 0 && row < data.length) {
            return data[row];
        }
        return null;
    }
}
