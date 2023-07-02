/*
 * FileName: synchronized同步静态方法
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈synchronized的应用方式二 静态同步方法，锁是当前类的class对象 ，进入同步代码前要获得当前类对象的锁〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class synchronized同步静态方法 {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized同步静态方法.m1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized同步静态方法.m2();
            }
        }).start();
    }

    /**
     * 在静态方法中, 可以使用常量作为锁对象, 还可以使用当前类的运行时类作为锁对象
     */
    private static void m1() {
        //每个类都有一个class属性, 它是当前类的运行时类对象
        //可以简单的理解为把Thread14Synchronized3的字节码作为锁对象, 有人也称它为类锁
        synchronized (synchronized同步静态方法.class) {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    /**
     * 使用synchronized修饰静态方法,表示整个方法体都进行了同步, 默认的锁对象是当前类的运行时类对象 ,synchronized同步静态方法.class
     */
    private synchronized static void m2() {
        for (int i = 1; i <= INT100; i++) {
            DealLog.log(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
