package com.satansk.concurrency.Java7_concurrent.Java7_2.synchronizedMethod;

import java.util.concurrent.TimeUnit;

/**
 * Author: Song
 * Date:   16:38 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * synchronized 保护的临界区
     *
     * @param amount 存款数目
     * @throws InterruptedException sleep() 方法抛出的中断异常
     */
    public synchronized void addAmount(double amount) throws InterruptedException {
        double tmp = balance;
        TimeUnit.MILLISECONDS.sleep(10);
        tmp += amount;
        balance = tmp;
    }

    /**
     * synchronized 保护的临界区
     *
     * @param amount 取款数目
     * @throws InterruptedException sleep() 方法抛出的中断异常
     */
    public synchronized void subtractAmount(double amount) throws InterruptedException {
        double tmp = balance;
        TimeUnit.MILLISECONDS.sleep(10);
        tmp -= amount;
        balance = tmp;
    }
}
