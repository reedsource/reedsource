/*
 * Author:   reedsource
 */
package j06底层并发.多线程;


import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈创建线程方式一, 继承Thread〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 创建线程实现继承Thread {
	public static void main(String[] args) {
		//3) 创建线程对象
		SubThread t1 = new SubThread();

		//4) 开启新的线程
		t1.start();   //start 出发，启程;

		//当前是main线程
		for (int i = 1; i <= INT100; i++) {
			DealLog.log("main-->" + i);
		}
	}
}

/**
 * 1)  定义一个子类继承Thread
 */
class SubThread extends Thread {
	/**
	 * 2) 重写run()
	 */
	@Override
	public void run() {
		// run()方法中的代码就是子线程要执行的代码
		for (int i = 1; i <= INT100; i++) {
			DealLog.log("输出:" + i);
		}
	}
}
