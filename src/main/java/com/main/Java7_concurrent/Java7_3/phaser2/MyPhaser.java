package com.main.Java7_concurrent.Java7_3.phaser2;

import java.util.concurrent.Phaser;

/**
 * Author:  satansk
 * Date:    13:47 at 2015/7/15
 * Email:   satansk@hotmail.com
 */
public class MyPhaser extends Phaser {
    /**
     * Phaser 的执行阶段向前推进是调用
     *
     * @param phase 当前 phase
     * @param registeredParties 注册的参与线程数目
     * @return  true: Phaser 结束，进入 termination 状态
     *          false: 继续向前推进
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        /**
         * 根据 phase 的不同，进行不同的操作
         */
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;

        }
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        /**
         * false 表明 Phaser 将继续向前推进
         */
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students have finished the first exercise.\n");
        System.out.printf("Phaser: It's time for the second exercise.\n");
        /**
         * false 表明 Phaser 将继续向前推进
         */
        return false;
    }

    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students have finished the second exercise.\n");
        System.out.printf("Phaser: It's time for the third one.\n");
        /**
         * false 表明 Phaser 将继续向前推进
         */
        return false;
    }

    private boolean finishExam() {
        System.out.printf("Phaser: All the students have finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        /**
         * true 表明 Phaser 执行完毕，进入 termination 状态
         */
        return true;
    }
}
