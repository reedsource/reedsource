/*
 * FileName: 多线程卖票
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程.Thread多线程模拟案例.多线程卖票;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT3;
import static top.ireed.general.TopConstant.INT40;

/**
 * 功能简述:
 * 〈多线程卖票问题〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程卖票 {

    public static void main(String[] args) {
        卖一张票 卖一张票 = new 卖一张票();

        for (int x = 1; x <= INT3; x++) {
            new Thread(() -> {
                // 打印40张票
                for (int i = 1; i <= INT40; i++) {
                    DealLog.log(Thread.currentThread().getName() + "-->" + 卖一张票);
                }
            }, "卖票").start();
        }
    }
}

class 卖一张票 {
    /**
     * 座号
     */
    int num = 0;

    @Override
    public String toString() {
        //使用当前对象作为锁对象, 调用toString()方法的对象
        synchronized (this) {
            num++;
            return "ticket : " + num;
        }
    }
}