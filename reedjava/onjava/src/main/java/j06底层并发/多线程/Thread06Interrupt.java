/*
 * FileName: Thread06Interrupt
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT200;

/**
 * 功能简述:
 * 〈线程睡眠与线程睡眠中断〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Thread06Interrupt {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= INT200; i++) {
					DealLog.log(Thread.currentThread().getName() + "-->" + i);
					if (i == 100) {
						try {
							//子线程睡眠10秒
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "t1");
		t1.start();

		//main线程
		for (int i = 1; i <= INT200; i++) {
			DealLog.log(Thread.currentThread().getName() + "-->" + i);
		}
		//当main线程的for循环结束后, 把t1线程的睡眠中断, 睡眠中断后 t1继续执行
		t1.interrupt();
	}
}
