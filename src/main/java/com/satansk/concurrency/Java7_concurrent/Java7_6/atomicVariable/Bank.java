package com.satansk.concurrency.Java7_concurrent.Java7_6.atomicVariable;

/**
 * Author:  satansk
 * Date:    16:29 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Bank implements Runnable {
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.subtractAmount(1000);
        }
    }
}
