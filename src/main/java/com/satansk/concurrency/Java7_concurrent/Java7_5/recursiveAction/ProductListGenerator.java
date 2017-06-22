package com.satansk.concurrency.Java7_concurrent.Java7_5.recursiveAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  satansk
 * Date:    9:07 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class ProductListGenerator {
    public List<Product> generate(int size) {
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
