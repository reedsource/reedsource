/*
 * FileName: 线程当前状态
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;


import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;
import static top.ireed.general.TopConstant.INT500;

/**
 * 功能简述:
 * 〈线程当前状态〉
 * <p>
 * NEW	            至今尚未启动的线程处于这种状态
 * RUNNABLE	        正在 Java 虚拟机中执行的线程处于这种状态。 因为可能在等待其他的资源， 比如处理器。
 * BLOCKED	        受阻塞并等待某个监视器锁的线程处于这种状态
 * WAITING	        无限期地等待另一个线程来执行。某一特定操作的线程处于这种状态
 * TIMED_WAITING	等待另一个线程来执行。取决于指定等待时间的操作的线程处于这种状态
 * TERMINATED	    已退出的线程处于这种状态
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 线程当前状态 {
    public static void main(String[] args) {

        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= INT100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DealLog.log(Thread.currentThread().getName() + "==>" + i);
                }
            }
        }, "t0");
        t0.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= INT500; i++) {
                    DealLog.log(Thread.currentThread().getName() + "-->" + i);
                    if (i == 300) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (i == 400) {
                        try {
                            t0.join();        //加入t0线程
                            //在t1线程中,加入了t0线程,t1线程就转为等待WAITING状态
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1");

        //NEW
        DealLog.log("线程未启动:" + t1.getState());

        t1.start();
        //RUNNABLE
        DealLog.log("线程启动后:" + t1.getState());

        try {
            //main线程睡眠
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //TIMED_WAITING
        DealLog.log("线程睡眠中:" + t1.getState());

        try {
            //main线程睡眠
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TERMINATED
        DealLog.log("线程结束后:" + t1.getState());
    }
}
