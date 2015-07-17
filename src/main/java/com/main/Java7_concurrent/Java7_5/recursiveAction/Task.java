package com.main.Java7_concurrent.Java7_5.recursiveAction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Author:  satansk
 * Date:    9:43 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Task extends RecursiveAction {
    // 声明类的序列版本 UID，因为 RecursiveAction 的父类 ForkJoinTask 实现了 Serializable 接口
    private static final long serialVersionUID = 1L;
    private List<Product> products;

    private int first;  // 要修改的价格列表区间
    private int last;
    private double increment;   // 价格增加的百分比

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (last + first) / 2;
            System.out.printf("%s: Task: pending tasks: %s\n",
                    Thread.currentThread().getName(),
                    getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            /**
             * 1. 执行本任务创建的子任务
             * 2. 所有子任务完成后，返回
             * 3. 等待子任务完成时，可以 steal 其他正在等待的任务来执行
             */
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
