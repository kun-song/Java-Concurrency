package com.satansk.concurrency.liveness;

/**
 * Author:  satansk
 * Email:   satansk@hotmail.com
 * Date:    17/6/22
 */
public class Deadlock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!\n", this.name, bower.getName());
            // 请求 bower 的锁
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!\n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend Alphonse = new Friend("Alphonse");
        final Friend Gaston = new Friend("Gaston");

        new Thread(new Runnable() {
            public void run() {
                Alphonse.bow(Gaston);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Gaston.bow(Alphonse);
            }
        }).start();
    }
}
