package com.satansk.concurrency.Java7_concurrent.Java7_2.synchronizedBlock;

/**
 * Author: Song
 * Date:   17:37 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class TicketOffice1 implements Runnable {
    private Cinema cinema;

    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
    }
}
