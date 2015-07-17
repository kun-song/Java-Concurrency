package com.main.Java7_concurrent.Java7_5.forkJoinPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  satansk
 * Date:    9:07 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class ProductListGenerator {
    public List<com.main.Java7_concurrent.Java7_5.forkJoinPool.Product> generate(int size) {
        List<com.main.Java7_concurrent.Java7_5.forkJoinPool.Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            com.main.Java7_concurrent.Java7_5.forkJoinPool.Product product = new com.main.Java7_concurrent.Java7_5.forkJoinPool.Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
