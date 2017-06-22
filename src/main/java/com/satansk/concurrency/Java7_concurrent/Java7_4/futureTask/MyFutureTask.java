package com.satansk.concurrency.Java7_concurrent.Java7_4.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Author:  satansk
 * Date:    11:27 at 2015/7/16
 * Email:   satansk@hotmail.com
 */
public class MyFutureTask extends FutureTask<String> {
    private String name;

    public MyFutureTask(Callable<String> callable) {
        super(callable);
        this.name = ((Task) callable).getName();
    }

    /**
     * 1. 任务状态转换为 isDone 之后被调用：无论任务是 (1) 正常完成  还是 (2) 被取消
     *
     * 2. 可以在这里做任务的后续处理：生成报告、通过 Email 发送结果、释放资源
     *
     * 3. 该方法在 FutureTask 中默认为空，一般都要继承 FutureTask，然后覆盖 done()
     */
    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s has been canceled\n", name);
        } else {
            System.out.printf("%s has finished\n", name);
        }
    }
}
