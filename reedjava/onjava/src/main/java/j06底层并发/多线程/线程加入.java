/*
 * FileName: 线程加入
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.*;

/**
 * 功能简述:
 * 〈线程加入〉
 * 在一个线程执行过程中 加入执行另一个线程
 * 执行另一线程时间段后 恢复主线程
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 线程加入 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < INT100; i++) {
                    DealLog.log(Thread.currentThread().getName() + ":" + i);
                    try {
                        Thread.sleep(INT10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");
        t1.start();

        //main线程
        for (int i = 0; i < INT100; i++) {
            DealLog.log(Thread.currentThread().getName() + "-->" + i);
            if (i == INT50) {
                //当main线程中的i变量为50时, 加入t1线程
                //原来 main线程和t1线程同时执行, 现在main线程变为等待状态, 只有t1线程在执行
                //当t1线程执行完后, main线程再转为就绪状态

                //最多等100毫秒, 如果100毫秒后t1线程还没结束 , 当前线程也会转为就绪状态  也可不设置等待时间 将永远等待
                t1.join(INT100);
            }
        }
    }
}
