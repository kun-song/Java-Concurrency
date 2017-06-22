package com.satansk.concurrency.Java7_concurrent.Java7_6.concurrentSkipListMap;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Author:  satansk
 * Date:    10:19 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Task implements Runnable {
    private ConcurrentSkipListMap<String, Contract> map;
    private String id;

    public Task(ConcurrentSkipListMap<String, Contract> map, String id) {
        this.map = map;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contract contract = new Contract(id, String.valueOf(i + 1000));
            /**
             * 1. put(key, value): 如果已经存在该 key，则更新该 key 上的 value 值
             */
            map.put(id + contract.getPhone(), contract);
        }
    }
}
