/*
 * FileName: 多线程取钱
 * Author:   reedsource
 */
package j06底层并发.多线程.Thread多线程模拟案例.多线程取钱;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈模拟多人同时从某个 帐户中取钱  线程安全问题〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程取钱 {
    public static void main(String[] args) {
        账户 n账户 = new 账户();

        取款Thread t1 = new 取款Thread(n账户);
        取款Thread t2 = new 取款Thread(n账户);
        取款Thread t3 = new 取款Thread(n账户);

        t1.setName("张三");
        t2.setName("李四");
        t3.setName("王五");

        t1.start();
        t2.start();
        t3.start();
		/*
	    一次的运行结果为:
		李四取钱前,余额为:10000
		王五取钱前,余额为:10000
		张三取钱前,余额为:10000
		王五取了1000元,余额为:8000
		李四取了1000元,余额为:9000
		张三取了1000元,余额为:7000

		现象:
			张三取钱前查询余额为10000,取了1000再查询余额为7000
			这种现象称为多线程安全问题
		分析原因:
			1) 多个线程同时操作堆中的同一个对象
			2) 在某个线程操作期间, 其他线程插入了
		解决方法:
			1) 不让多个线程同时操作堆区/方法区 中的同一个数据, 各个线程都操作局部变量不会出现线程安全问题
			2) 如果多个线程必须同时操作堆区/方法区的同一个数据, 保存一个线程在操作过程中,不允许其他的线程加入

			使用线程同步解决  见下方注释
		 */
    }

    /**
     * 功能简述:
     * 〈〉
     *
     * @author reedsource
     * @version 1.0.0
     * date 2022/5/24 20:58
     * reedsource@189.cn
     */
    static class 取款Thread extends Thread {
        /**
         * 定义帐户
         */
        private final 账户 n账户;

        /**
         * 构造方法
         *
         * @param 账户to 余额对象
         */
        取款Thread(账户 账户to) {
            super();
            this.n账户 = 账户to;
        }

        @Override
        public void run() {
            // 从帐户中取钱
            n账户.取钱事件();
        }
    }

    /**
     * 功能简述:
     * 〈〉
     *
     * @author reedsource
     * @version 1.0.0
     * date 2022/5/24 21:01
     * reedsource@189.cn
     */
    static class 账户 {
        /**
         * 余额
         */
        private int 余额 = 10000;

        /**
         * 取钱, 约定每次取1000
         * ==============问题解决思路 本处添加synchronized    线程同步===============
         */
        void 取钱事件() {
            DealLog.log(Thread.currentThread().getName() + "取钱前,余额为:" + 余额);
            余额 = 余额 - 1000;
            DealLog.log(Thread.currentThread().getName() + "取了1000元,余额为:" + 余额);
        }
    }
}

