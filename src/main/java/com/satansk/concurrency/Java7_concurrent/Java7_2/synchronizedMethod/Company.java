package com.satansk.concurrency.Java7_concurrent.Java7_2.synchronizedMethod;

/**
 * Author: Song
 * Date:   16:46 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Company implements Runnable {
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                /**
                 * 1. 访问 addAmount() 时获取 intrinsic lock
                 * 2. addAmount() 结束之后自动释放 intrinsic lock
                 */
                account.addAmount(1000);
                System.out.printf("Company: %f\n", account.getBalance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
