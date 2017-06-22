package com.satansk.concurrency.Java7_concurrent.Java7_2.synchronizedMethod;

/**
 * Author: Song
 * Date:   16:39 at 2015/7/13
 * Email:  satansk@hotmail.com
 *
 * 1. ATM Company 线程分别进行了 加、减 100 次 1000，最后，余额应该跟以前相同。
 * 2. 如果 Account 中的 add subtract 没有使用同步机制，则最后的余额数据是随机的
 *
 * 3. ATM Company 中的 add sub 是交替阻塞的，ATM Company 两个线程也是交替阻塞的，查看运行结果一目了然
 */
public class Bank{
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        account.setBalance(1000);
        Thread company = new Thread(new Company(account));
        Thread atm = new Thread(new com.main.Java7_concurrent.Java7_2.synchronizedMethod.ATM(account));

        System.out.printf("initial balance: %f\n", account.getBalance());

        company.start();
        atm.start();

        company.join();
        atm.join();

        System.out.printf("final balance: %f\n", account.getBalance());
    }
}
