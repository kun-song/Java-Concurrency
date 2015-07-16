package com.main.Java7_concurrent.Java7_2.synchronizedBlock;

/**
 * Author: Song
 * Date:   17:39 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema();
        com.main.Java7_concurrent.Java7_2.synchronizedBlock.TicketOffice1 ticketOffice1 = new com.main.Java7_concurrent.Java7_2.synchronizedBlock.TicketOffice1(cinema);
        com.main.Java7_concurrent.Java7_2.synchronizedBlock.TicketOffice2 ticketOffice2 = new com.main.Java7_concurrent.Java7_2.synchronizedBlock.TicketOffice2(cinema);

        Thread thread1 = new Thread(ticketOffice1);
        Thread thread2 = new Thread(ticketOffice2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());
    }
}
