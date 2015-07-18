package com.main.Java7_concurrent.Java7_6.atomicVariable;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author:  satansk
 * Date:    16:25 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * 1. Java 中一般变量操作都不是原子操作，比如 i++ 在 JVM 中分为： read modify write 三个步骤，这样一来，
 *      在并发环境中，多个线程同时操作共享变量就会造成这些操作的交叉进行，从而产生数据不一致问题。
 * 2. 可以通过同步机制来避免
 *
 * 3. 更好的办法是直接使用 Java 中的原子类，如 AtomicLong 等，他们保证了操作的原子性，多线程访问线程安全
 */
public class Account {
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    public long getBalance() {
        return balance.get();
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }
}
