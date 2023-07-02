/*
 * FileName: 创建线程实现Runnable接口
 * Author:   reedsource
 */
package j06底层并发.多线程;


import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈创建线程方式二, 实现Runnable接口〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 创建线程实现Runnable接口 {
    public static void main(String[] args) {
        //3) 创建线程对象
        Prime target = new Prime();
        Thread t2 = new Thread(target);
        // 4) 调用start()开启新的线程
        t2.start();

        // 当前是main线程
        for (int i = 1; i <= INT100; i++) {
            DealLog.log("main-->" + i);
        }
    }
}

/**
 * 1) 定义一个类实现Runnable
 */
class Prime implements Runnable {
    /**
     * 2) 重写run()方法
     */
    @Override
    public void run() {
        for (int i = 1; i <= INT100; i++) {
            DealLog.log("sub thread:" + i);
        }
    }
}
