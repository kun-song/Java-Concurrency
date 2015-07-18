package com.main.Java7_concurrent.Java7_6.atomicVariable;

/**
 * Author:  satansk
 * Date:    16:28 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Company implements Runnable {
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAmount(1000);
        }
    }
}
