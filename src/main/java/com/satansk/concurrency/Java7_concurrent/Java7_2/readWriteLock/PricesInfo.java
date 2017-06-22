package com.satansk.concurrency.Java7_concurrent.Java7_2.readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: Song
 * Date:   20:29 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * ReentrantReadWriteLock 内部持有一对相关锁，分别用于读写，实现了读写分离：
 *
 *  1. readLock() 获取 readLock，用于同步 read 操作
 *  2. writeLock() 获取 writeLock，用于同步 write 操作
 */
public class PricesInfo {
    private double price1;
    private double price2;
    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}
