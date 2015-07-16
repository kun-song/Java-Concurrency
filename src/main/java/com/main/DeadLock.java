package com.main;

/**
 * Author:  satansk
 * Date:    10:34 at 2015/7/2
 * Email:   satansk@hotmail.com
 */
public class DeadLock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }
        public String getName() { return name;}

        public synchronized void bow(Friend bower) {
            System.out.printf("%s: %s " + "has bowed to me!\n", name, bower.getName());

            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.printf("%s: %s " + "has bowed back to me!\n", name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend a = new Friend("a");
        final Friend b = new Friend("b");

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.bow(b);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                b.bow(a);
            }
        }).start();
    }
}
