package com.main.Java7_concurrent.Java7_6.atomicVariable;

import java.util.TreeMap;

/**
 * Author:  satansk
 * Date:    16:30 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Bank bank = new Bank(account);

        Thread companyThread = new Thread(company);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account: initial balance = %d\n", account.getBalance());

        companyThread.start();
        bankThread.start();

        companyThread.join();
        bankThread.join();

        System.out.printf("Account: final balance = %d\n", account.getBalance());
    }
}
