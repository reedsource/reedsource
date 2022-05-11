/*
 * FileName: Thread01Ordinary
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈普通的多线程实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Thread01Ordinary {


	public static void main(String[] args) {
		thread01();
	}

	/**
	 * 创建线程的基本流程及中间可用的常用方法
	 * Thread.currentThread()   返回当前线程
	 * getName()                返回线程名称
	 * getId() 	                返回线程的ID
	 * setName() 	            设置线程名称
	 * Thread.activeCount()	    返回活动线程的数量
	 * isAlive()	            判断线程是否在运行
	 * start()                  线程启动
	 * Thread.sleep(2000);      线程睡眠
	 */
	private static void thread01() {
		//活动线程的数量
		DealLog.log("简单线程01启动前 线程数量:" + Thread.activeCount());

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= INT100; i++) {
					DealLog.log(Thread.currentThread().getName() + " : " + Thread.currentThread().getId() + "--> " + i);
					if (i == 50) {
						DealLog.log(Thread.currentThread().getName() + "运行?" + Thread.currentThread().isAlive());
						Thread.currentThread().setName("改名的简单线程1");
					}
				}
			}
		}, "简单线程01");

		DealLog.log("线程执行前执行");

		//线程启动
		t1.start();

		//外部的当前线程为man主线程
		DealLog.log(Thread.currentThread().getName() + "运行?" + Thread.currentThread().isAlive());

		DealLog.log("简单线程01启动后 线程数量: " + Thread.activeCount());

		try {
			//sleep()静态方法,跟调用对象没有关系 , 规范用法通过类名调用
			//通过对象名可以调用,跟t1没有任何关系 , sleep()方法所在的线程睡眠
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//当前简单线程01已经执行完成,自动关闭了
		DealLog.log("主线程休眠2秒后 线程数量: " + Thread.activeCount());
	}
}
