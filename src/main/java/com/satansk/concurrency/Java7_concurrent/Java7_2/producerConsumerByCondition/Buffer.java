package com.satansk.concurrency.Java7_concurrent.Java7_2.producerConsumerByCondition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:  satansk
 * Date:    10:52 at 2015/7/14
 * Email:   satansk@hotmail.com
 *
 * 1. Condition 对象与 Lock 息息相关，Condition 使用 Lock.newCondition() 来创建。
 * 2. 使用 Condition 之前，必须首先获取该 Condition 关联的 Lock，对于 Condition 的操作，都是包含在
 *      lock() 与 unlock() 之间的。
 *
 * 3. 线程在 Condition 上调用 await() 时，自动释放锁，其他线程可以获取该锁。
 * 4. 线程在 Condition 上调用 signal() signalAll() 时，所有在该 Condition 上等待的线程都被唤醒，但是他们
 *      之间只有一个能获取锁，其他的线程将再次调用 Condition.await() 再次睡眠。
 *
 * 5. 如果在一个 Condition 上调用 await()，却忘记了调用 signal()，则线程将会永久沉睡。
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;    // 缓冲区长度
    private ReentrantLock lock; // 控制缓冲区的修改代码
    private Condition lines;
    private Condition space;
    private boolean pendingLines;   // 是否有未处理的行

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    /**
     * 将字符串 line 存入缓冲区。
     * 1. 获取锁
     * 2. 检测缓冲区是否已满，满，则调用 Condition.await()，使本线程在 space 条件上等待释放空间
     *      等到其他线程调用 space.signal() space.signalAll() 时，该线程将被唤醒
     * 3. 缓冲区未满，则将 line 存入缓冲区，然后调用 Condition.signalAll() 唤醒在 lines 条件上等待的线程
     *
     * @param line 存入缓冲区的字符串
     */
    public void insert(String line) {
        lock.lock();

        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            System.out.println("space.await() has been interrupted!");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 返回缓冲区上的第一个字符串。
     * 1. 获取锁
     * 2. 检测缓冲区是否为空，若为空，则调用 Condition.await() 在 lines 条件上等待
     * 3. 非空，则判断是否有未处理的行，若有，则取出缓冲区的第一个字符串返回；若无，直接跳过
     *
     * @return 缓冲区第一个字符串
     */
    public String get() {
        String line = null;
        lock.lock();
        try {
            while (buffer.size() == 0) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Read: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            System.out.println("lines.await() has been interrupted!");
        } finally {
            lock.unlock();
        }

        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }
}