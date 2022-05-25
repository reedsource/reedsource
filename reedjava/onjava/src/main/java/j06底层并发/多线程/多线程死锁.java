/*
 * FileName: 多线程死锁
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.S_A;
import static top.ireed.general.TopConstant.S_B;

/**
 * 功能简述:
 * 〈多线程死锁〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程死锁 {
	public static void main(String[] args) {
		Thread t1 = new DeadlockThread();
		t1.setName(S_A);
		t1.start();

		Thread t2 = new DeadlockThread();
		t2.setName(S_B);
		t2.start();
	}
}

/**
 * 死锁专用多线程
 */
class DeadlockThread extends Thread {
	@Override
	public void run() {
		if (S_A.equals(Thread.currentThread().getName())) {
			synchronized ("资源1") {
				DealLog.log("线程a获得了资源1, 还想获得资源2");
				synchronized ("资源2") {
					DealLog.log("线程a同时获得了资源1与资源2, 爽歪歪~~~");
				}
			}
		}
		if (S_B.equals(Thread.currentThread().getName())) {
			synchronized ("资源2") {
				DealLog.log("线程b获得了资源2, 还想获得资源1");
				synchronized ("资源1") {
					DealLog.log("线程b同时获得了资源1与资源2, 爽歪歪~~~");
				}
			}
		}

	}
}
