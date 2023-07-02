/*
 * FileName: 多线程优先级设置
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT200;

/**
 * 功能简述:
 * 〈多线程优先级设置〉
 * setPriority() 设置线程的优先级
 * 线程优先级 默认: 5
 * 优先级越高, 获得CPU执行权的机率越大
 * <p>
 * 案例效果 t1将最后执行完成
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程优先级设置 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= INT200; i++) {
                    DealLog.log(Thread.currentThread().getName() + "-->" + i);
                }
            }
        }, "t1");
        t1.setPriority(1);
        t1.start();
        DealLog.log("t1:" + t1.getPriority());

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= INT200; i++) {
                    DealLog.log(Thread.currentThread().getName() + "-->" + i);
                }
            }
        }, "t2");
        t2.setPriority(10);
        t2.start();
        DealLog.log("t2:" + t2.getPriority());

        //main
        for (int i = 1; i <= INT200; i++) {
            DealLog.log(Thread.currentThread().getName() + "-->" + i);
        }

        DealLog.log("main:" + Thread.currentThread().getPriority());
    }
}
