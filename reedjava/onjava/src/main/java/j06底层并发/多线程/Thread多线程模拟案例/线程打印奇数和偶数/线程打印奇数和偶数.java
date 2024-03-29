/*
 * FileName: 线程打印奇数和偶数
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程.Thread多线程模拟案例.线程打印奇数和偶数;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈多线程打印奇数和偶数〉
 * <p>
 * wait()方法调用时，会释放线程获得的锁，wait()方法返回后，线程又会重新试图获得锁
 * notify()唤醒一个正在this.wait()等待锁的线程
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 线程打印奇数和偶数 {
    public static void main(String[] args) {
        打印数 打印数 = new 打印数();

        //创建一个线程,打印奇数
        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                打印数.printOdd();
            }
        }).start();
        //创建一个线程,打印偶数
        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                打印数.printEven();
            }
        }).start();
    }
}

/**
 * 打印数类
 */
class 打印数 {

    /**
     * 当前计数
     */
    private int num = 0;

    /**
     * 打印奇数
     */
    synchronized void printOdd() {
        //如果当前的数是偶数,就等待
        while (num % 2 == 0) {
            try {
                //wait()方法调用时，会释放线程获得的锁，wait()方法返回后，线程又会重新试图获得锁
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印奇数
        DealLog.log(Thread.currentThread().getName() + ":" + num);
        num++;
        //通知打印偶数 唤醒一个正在this.wait()等待锁的线程
        this.notify();
    }

    /**
     * 打印偶数
     */
    synchronized void printEven() {
        //如果当前的数是奇数,就等待
        while (num % 2 != 0) {
            try {
                //wait()方法调用时，会释放线程获得的锁，wait()方法返回后，线程又会重新试图获得锁
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印偶数
        DealLog.log(Thread.currentThread().getName() + ":" + num);
        num++;
        //通知打印奇数  唤醒一个正在this.wait()等待锁的线程
        this.notify();
    }
}