package com.main.Java7_concurrent.Java7_3.CyclicBarrier;

/**
 * Author:  satansk
 * Date:    19:23 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Result {
    private int[] data;

    public Result(int size) {
        this.data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
