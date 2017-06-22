package com.satansk.concurrency.Java7_concurrent.Java7_2.producerConsumerByCondition;

/**
 * Author: Song
 * Date:   23:11 at 2015/7/13
 * Email:  satansk@hotmail.com
 */
public class FileMock {
    private String[] content;   // 模拟文件内容
    private int index;  // 被检索到的行数

    /**
     * 用随机字符来初始化文件
     *
     * @param size 数组大小（字符串行数）
     * @param length 每行字符串长度
     */
    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                sb.append((char) indice);
            }
            content[i] = sb.toString();
        }
        index = 0;
    }

    /**
     * 查看文件是否有更多未处理行
     *
     * @return true 有未处理行  false 处理完毕
     */
    public boolean hasMoreLine() {
        return index < content.length;
    }

    /**
     * 返回 index 指向的那行内容，并且将 index + 1 从而指向下一行
     * @return  index 的内容
     *          若处理完毕，返回 null
     */
    public String getLine() {
        if (hasMoreLine()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}
