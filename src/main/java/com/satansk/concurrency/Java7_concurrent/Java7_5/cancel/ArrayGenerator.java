package com.satansk.concurrency.Java7_concurrent.Java7_5.cancel;

import java.util.Random;

/**
 * Author:  satansk
 * Date:    19:03 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class ArrayGenerator {
    public int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }

        return array;
    }
}
