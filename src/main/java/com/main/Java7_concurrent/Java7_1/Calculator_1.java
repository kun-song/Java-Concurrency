package com.main.Java7_concurrent.Java7_1;

/**
 * Author:  satansk
 * Date:    16:08 at 2015/7/10
 * Email:   satansk@hotmail.com
 *
 * 1 * 1 = 1
 * 1 * 2 = 2
 * 1 * 3 = 3
 * ...
 * 2 * 1 = 2
 * 2 * 2 = 4
 * ...
 *
 * 每个线程负责计算 number * 1-10，但是这个 10 个线程之间的运行顺序是无法确定的，他们互相交织运行
 */
public class Calculator_1 implements Runnable {
    private int number;

    public Calculator_1(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d\n",
                    Thread.currentThread().getName(),
                    number,
                    i,
                    i * number);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Calculator_1 calculator_1 = new Calculator_1(i);
            new Thread(calculator_1).start();
        }
    }
}
