/*
 * FileName: Synchronized同锁对象实现同步
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈不同同步代码块 , 只要是锁对象是同一个就可以实现同步〉
 * <p>
 * 同步时 先顶级同步 然后遍历下级同步
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Synchronized同锁对象实现同步 {
    public static void main(String[] args) {
        MyPrintM mPrint1 = new MyPrintM();
        //一个线程调用m1()
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPrint1.m1();
            }
        }, "线程1").start();

        //另一个线程调用m2()
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyPrintM.m2();
            }
        }, "线程2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyPrintN.m3();
            }
        }, "线程3").start();
    }
}

class MyPrintM {

    /**
     * 常量
     */
    static final Object OBJ = new Object();

    void m1() {
        //使用常量作为锁对象
        synchronized (OBJ) {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    static void m2() {
        //使用常量作为锁对象
        synchronized (OBJ) {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }
}

class MyPrintN {

    static void m3() {
        //使用常量作为锁对象
        synchronized (MyPrintM.OBJ) {
            for (int i = 1; i <= INT100; i++) {
                DealLog.log(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }
}