package com.satansk.concurrency.Java7_concurrent.Java7_6.delayedQueue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Author:  satansk
 * Date:    9:24 at 2015/7/18
 * Email:   satansk@hotmail.com
 *
 * 1. 存储在 DelayQueue 中的元素必须实现 Delayed 接口
 * 2. Delayed 接口继承 Comparable 接口
 */
public class Event implements Delayed {
    private Date startDate; // 预定的激活时间

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 剩余延迟时间，以 纳秒 为单位
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startDate.getTime() - new Date().getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * 根据 getDelay() 方法进行比较
     */
    @Override
    public int compareTo(Delayed o) {
        long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
