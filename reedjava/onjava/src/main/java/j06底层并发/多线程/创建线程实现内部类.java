/*
 * FileName: 创建线程实现内部类
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;


import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈多线程创建其他实现方式〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 创建线程实现内部类 {
    public static void main(String[] args) {
        // 1) Thread的匿名内部类
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= INT100; i++) {
                    DealLog.log("Thread的匿名内部类:" + i);
                }
            }
        };
        t1.start();

        //2) Runnable接口的匿名内部类
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= INT100; i++) {
                    DealLog.log("Runnable接口的匿名内部类 ==>" + i);
                }
            }
        });
        t2.start();

        //3) 内部类
        Thread t3 = new Thread(new Prime3());
        t3.start();

    }

    /**
     * 静态内部类
     */
    static class Prime3 implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log("内部类 --->" + i);
            }
        }
    }
}
