/*
 * FileName: ThreadCase02Issue
 * Author:   reedsource
 */
package j06底层并发.多线程.Thread多线程模拟案例.多线程取钱;

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
public class ThreadCase02Issue {
	public static void main(String[] args) {
		MyAccount1 myaccount1 = new MyAccount1();

		SubThread1 t1 = new SubThread1(myaccount1);
		SubThread1 t2 = new SubThread1(myaccount1);
		SubThread1 t3 = new SubThread1(myaccount1);

		t1.setName("张三");
		t2.setName("李四");
		t3.setName("王五");

		t1.start();
		t2.start();
		t3.start();
	}
}

class SubThread1 extends Thread {
	/**
	 * 定义帐户
	 */
	private MyAccount1 myaccount;

	/**
	 * 构造方法
	 *
	 * @param myaccount
	 */
	SubThread1(MyAccount1 myaccount) {
		super();
		this.myaccount = myaccount;
	}

	@Override
	public void run() {
		// 从帐户中取钱
		myaccount.withdraw();
	}

}

class MyAccount1 {
	/**
	 * 余额
	 */
	private int balance = 10000;

	/**
	 * 定义常量, 作为锁对象
	 */
	private static final Object LOCK = new Object();

	/**
	 * 取钱, 约定每次取1000
	 */
	void withdraw() {
		synchronized (LOCK) {
			DealLog.log(Thread.currentThread().getName() + "取钱前,余额为:" + balance);
			balance = balance - 1000;
			DealLog.log(Thread.currentThread().getName() + "取了1000元,余额为:" + balance);
		}
	}
}
