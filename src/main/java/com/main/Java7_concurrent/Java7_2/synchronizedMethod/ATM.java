package com.main.Java7_concurrent.Java7_2.synchronizedMethod;

/**
 * Author: Song
 * Date:   16:45 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class ATM implements Runnable {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                /**
                 * 1. 访问 subtractAmount() 时获取 intrinsic lock
                 * 2. subtractAmount() 结束之后自动释放 intrinsic lock
                 */
                account.subtractAmount(1000);
                System.out.printf("ATM: %f\n", account.getBalance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
