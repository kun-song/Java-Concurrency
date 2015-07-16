package com.main.Java7_concurrent.Java7_2.producerConsumerByCondition;

/**
 * Author:  satansk
 * Date:    11:35 at 2015/7/14
 * Email:   satansk@hotmail.com
 */
public class Producer implements Runnable {
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        /**
         * 1. 读取 mock 中的模拟字符串，插入 buffer 中
         * 2. 结束之后，调用 setPendingLines(false) 警告缓冲区，不会再产生更多行
         */
        buffer.setPendingLines(true);
        while (mock.hasMoreLine()) {
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
