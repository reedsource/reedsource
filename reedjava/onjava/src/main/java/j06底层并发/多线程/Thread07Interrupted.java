/*
 * FileName: Thread07Interrupted
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT200;

/**
 * 功能简述:
 * 〈线程中断方式及效果〉
 * Thread.interrupted() , 检查当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态，第二次再调用时中断状态已经被清除，将返回一个false。
 * Thread.currentThread().isInterrupted(), 只检查当前线程是否被中断（检查中断标志）,不改变中断状态
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Thread07Interrupted {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//查询线程中断状态, 查询后中断状态将被打断,线程中断状态为false
				while (!Thread.interrupted()) {
					DealLog.log(Thread.currentThread().getName() + " -- 正在运行");
				}
				//静态方法, 清空中断标志, 再判断时,为false
				DealLog.log("t1--线程中断->" + Thread.currentThread().isInterrupted());
			}
		}, "t1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//查询线程中断状态, 查询后中断状态不变,线程中断状态为true
				while (!Thread.currentThread().isInterrupted()) {
					DealLog.log(Thread.currentThread().getName() + " -- 正在运行");
				}
				//实例方法,不清空中断标志, 再判断仍然为true
				DealLog.log("t2--线程中断->" + Thread.currentThread().isInterrupted());
			}
		}, "t2");
		t2.start();

		//main线程
		for (int i = 1; i <= INT200; i++) {
			DealLog.log(Thread.currentThread().getName() + ":" + i);
		}
		//main线程的for循环一结束,就把t1线程中断
		t1.interrupt();
		t2.interrupt();
	}
}
