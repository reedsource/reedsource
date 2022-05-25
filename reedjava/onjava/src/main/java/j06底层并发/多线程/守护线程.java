/*
 * FileName: 守护线程
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈java守护线程〉
 * 当非守护线程停止,守护线程才停止
 * <p>
 * 本程序 t2被设置为守护线程 t1和main执行完成后 t2自动结束
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 守护线程 {
	public static void main(String[] args) {
		//用户线程
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= INT100; i++) {
					DealLog.log(Thread.currentThread().getName() + "--> " + i);
				}
			}
		}, "t1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= INT100; i++) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					DealLog.log(Thread.currentThread().getName() + "--> " + i);
				}
			}
		}, "t2");

		//把t2线程设置为守护线程
		t2.setDaemon(true);

		t2.start();

		//main线程
		for (int i = 1; i <= INT100; i++) {
			DealLog.log(Thread.currentThread().getName() + "--> " + i);
		}
	}
}
