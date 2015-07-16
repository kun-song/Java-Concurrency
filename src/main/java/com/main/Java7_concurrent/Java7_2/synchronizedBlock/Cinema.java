package com.main.Java7_concurrent.Java7_2.synchronizedBlock;

/**
 * Author: Song
 * Date:   17:25 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Cinema {
    /**
     * 1. 对 vacanciesCinema1 的修改在 controlCinema1 上进行同步
     * 2. 对 vacanciesCinema2 的修改在 controlCinema2 上进行同步
     *
     * 3. 因为对 vacanciesCinema1 & vacanciesCinema2 的访问使用了不同的同步对象，所以同一时刻可以有两个不同的线程
     *      分别对 vacanciesCinema1 & vacanciesCinema2 进行修改
     *
     * 4. 为什么用两个对象同步？
     *      因为如果使用 synchronized(this)，则同一时刻只能有一个线程访问，而实际 vacanciesCinema1 & vacanciesCinema2
     *      是两个独立属性，他们的修改互不影响，可以同时被两个不同线程访问。
     */
    private long vacanciesCinema1;
    private long vacanciesCinema2;

    private final Object controlCinema1;
    private final Object controlCinema2;

    public Cinema() {
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
        controlCinema1 = new Object();
        controlCinema2 = new Object();
    }

    /**
     * Cinema1 出售电影票方法，在 controlCinema1 对象上同步
     *
     * @param number 出售电影票数量
     * @return true: 出售成功 or false: 余票不足，出售失败
     */
    public boolean sellTickets1(int number) {
        synchronized (controlCinema1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Cinema2 出售电影票方法，在 controlCinema2 对象上同步
     *
     * @param number 出售电影票数量
     * @return true: 出售成功 or false: 余票不足，出售失败
     */
    public boolean sellTickets2(int number) {
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Cinema1 退票方法，在 controlCinema1 对象上同步
     *
     * @param number 退票数量
     * @return true: 退票成功
     */
    public boolean returnTickets1(int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    /**
     * Cinema2 退票方法，在 controlCinema2 对象上同步
     *
     * @param number 退票数量
     * @return true: 退票成功
     */
    public boolean returnTickets2(int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}
