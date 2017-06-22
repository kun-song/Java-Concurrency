package com.satansk.concurrency.Java7_concurrent.Java7_5.recursiveTask;

import java.util.Random;

/**
 * Author:  satansk
 * Date:    14:09 at 2015/7/17
 * Email:   satansk@hotmail.com
 */
public class Document {
    // 用于生成 String 二位数组
    private String words[] = {"the", "hello", "goodbye", "packt",  "java", "thread", "pool",
            "random", "class", "main"};

    public String[][] generateDocument(int numLines, int numWords, String word) {
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();

        for (int i = 0; i < numLines; i++){
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)){   // 统计 word 出现次数
                    counter++;
                }
            }
        }

        System.out.println("DocumentMock: The word appears " + counter + " times in the document");

        return document;
    }
}
