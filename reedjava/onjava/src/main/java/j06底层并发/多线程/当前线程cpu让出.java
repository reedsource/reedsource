/*
 * FileName: 当前线程cpu让出
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT200;

/**
 * 功能简述:
 * 〈当前线程让出当前cpu占用,但是让出后的cpu被哪个线程占用,无法确定 也就是当前线程依然有可能抢到当前程序让出的cpu 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 当前线程cpu让出 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= INT200; i++) {
                    DealLog.log(Thread.currentThread().getName() + ":" + i);
                }
            }
        }, "t1");
        t1.start();

        //main线程
        for (int i = 1; i <= INT200; i++) {
            DealLog.log(Thread.currentThread().getName() + ":" + i);
            //如果i除以10的余数为0
            if (i % 10 == 0) {
                //main线程让步
                Thread.yield();
            }
        }
    }
}
