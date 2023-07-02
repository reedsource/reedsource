/*
 * FileName: synchronized同步方法块
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;


import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * <p>
 * 〈synchronized的应用方式三 同步方法块，锁是括号里面的对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class synchronized同步方法块 {
    public static void main(String[] args) {
        synchronized同步方法块 obj = new synchronized同步方法块();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.m1();
            }
        }, "线程1").start();

        //这里虽然线程2调用了对象的m2方法, 但是因为线程1已经调用了对象中的m1()方法, 只有线程1执行完成后, 对象锁解除 才会执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.m2();
            }
        }, "线程2").start();
    }

    /**
     * 在实例方法m1()中, 整个方法体都进行了同步, 并且 使用this对象作为锁对象
     */
    public void m1() {
        synchronized (this) {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    /**
     * 使用synchronized修饰实例方法,表示整个方法体都进行了同步, 默认的锁对象是this对象
     */
    public synchronized void m2() {
        for (int i = 1; i <= INT100; i++) {
            DealLog.log(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
