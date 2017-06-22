package com.satansk.concurrency.Java7_concurrent.Java7_7.threadFactoryInExecutor;

import java.util.Date;

/**
 * Author:  satansk
 * Date:    19:49 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class MyThread extends Thread {
    private Date createTime;
    private Date startTime;
    private Date finishTime;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreateTime();
    }

    @Override
    public void run() {
        setStartTime();
        // 调用 super.run() 来实际执行 Runnable 任务？
        super.run();
        setFinishTime();
    }

    public void setCreateTime() {
        this.createTime = new Date();
    }

    public void setStartTime() {
        this.startTime = new Date();
    }

    public void setFinishTime() {
        this.finishTime = new Date();
    }

    public long getExecutionTime() {
        return finishTime.getTime() - startTime.getTime();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(": ");
        sb.append("createTime = ");
        sb.append(createTime);
        sb.append(";   execution time = ");
        sb.append(getExecutionTime());
        sb.append(" Milliseconds.");
        return sb.toString();
    }
}
