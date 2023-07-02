/*
 * FileName: 多线程取钱锁方案
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程.Thread多线程模拟案例.多线程取钱;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈模拟多人同时从某个 帐户中取钱  线程安全问题  解决方案2〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程取钱锁方案 {
    public static void main(String[] args) {
        锁账户 锁账户n = new 锁账户();

        取款锁Thread t1 = new 取款锁Thread(锁账户n);
        取款锁Thread t2 = new 取款锁Thread(锁账户n);
        取款锁Thread t3 = new 取款锁Thread(锁账户n);

        t1.setName("张三");
        t2.setName("李四");
        t3.setName("王五");

        t1.start();
        t2.start();
        t3.start();
    }
}

class 取款锁Thread extends Thread {
    /**
     * 定义帐户
     */
    private final 锁账户 n锁账户;

    /**
     * 构造方法
     *
     * @param n锁账户 余额对象
     */
    取款锁Thread(锁账户 n锁账户) {
        super();
        this.n锁账户 = n锁账户;
    }

    @Override
    public void run() {
        // 从帐户中取钱
        n锁账户.锁取钱事件();
    }

}

class 锁账户 {
    /**
     * 余额
     */
    private int 余额 = 10000;

    /**
     * 定义常量, 作为锁对象
     */
    private static final Object LOCK = new Object();

    /**
     * 取钱, 约定每次取1000
     */
    void 锁取钱事件() {
        synchronized (LOCK) {
            DealLog.log(Thread.currentThread().getName() + "取钱前,余额为:" + 余额);
            余额 = 余额 - 1000;
            DealLog.log(Thread.currentThread().getName() + "取了1000元,余额为:" + 余额);
        }
    }
}
