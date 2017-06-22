package com.satansk.concurrency.Java7_concurrent.Java7_6.concurrentSkipListMap;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Author:  satansk
 * Date:    10:21 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentSkipListMap<String, Contract> map = new ConcurrentSkipListMap<>();

        Thread[] threads = new Thread[25];

        int counter = 0;
        for (char i = 'A'; i < 'Z'; i++) {
            /**
             * 1. String.valueOf(char c): 返回字符 c 的字符串表示
             */
            Task task = new Task(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.printf("Main: size of the map: %d\n", map.size());

        Map.Entry<String, Contract> element;
        Contract contract;
        element = map.firstEntry();
        contract = element.getValue();
        System.out.printf("Main: first entry: (%s, %s)\n", contract.getName(), contract.getPhone());

        element = map.lastEntry();
        contract = element.getValue();
        System.out.printf("Main: last entry: (%s, %s)\n", contract.getName(), contract.getPhone());

        ConcurrentNavigableMap<String, Contract> subMap = map.subMap("A1996", "B1002");
        do {
            /**
             * 1. poll: remove and return
             */
            element = subMap.pollFirstEntry();
            if (element != null) {
                contract = element.getValue();
                System.out.printf("(%s, %s)\n", contract.getName(), contract.getPhone());
            }
        } while (element != null);
    }
}
