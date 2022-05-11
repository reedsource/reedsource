/*
 * FileName: Thread12Synchronized1
 * Author:   reedsource
 */
package j06底层并发.多线程;

import top.ireed.deal.DealLog;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈synchronized同步锁〉
 * 关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile
 * <p>
 * synchronized的应用方式一 普通同步方法（实例方法），锁是当前实例对象 ，进入同步代码前要获得当前实例的锁
 * 注意在方法声明中同步（synchronized ）关键字。这告诉Java该方法是同步的。
 * <p>
 * Java实例方法同步是同步在拥有该方法的对象上。这样，每个实例其方法同步都同步在不同的对象上，即该方法所属的实例。
 * 只有一个线程能够在实例方法同步块中运行。如果有多个实例存在，那么一个线程一次可以在一个实例同步块中执行操作。一个实例一个线程
 * <p>
 * m1()方法的锁对象是this对象,
 * m2()方法的锁对象是this对象,
 * <p>
 * 创建了两个线程,分别调用同一个 对象mPrint1的 m1()/m2()
 * 他们的锁对象都是mPrint1对象 ,可以同步
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Thread12Synchronized1 extends Thread {
	public static void main(String[] args) {
		Synchronized aSynchronized = new Synchronized();
		//一个线程调用m1()
		new Thread(new Runnable() {
			@Override
			public void run() {
				aSynchronized.m1();
			}
		}).start();

		//另一个线程调用m2()
		new Thread(new Runnable() {
			@Override
			public void run() {
				aSynchronized.m2();
			}
		}).start();
	}
}

class Synchronized {
	public void m1() {
		//使用this对象作为锁对象
		synchronized (this) {
			for (int i = 1; i <= INT100; i++) {
				DealLog.log(Thread.currentThread().getName() + "-->" + i);
			}
		}
	}

	public void m2() {
		//使用this对象作为锁对象
		synchronized (this) {
			for (int i = 1; i <= INT100; i++) {
				DealLog.log(Thread.currentThread().getName() + "-->" + i);
			}
		}
	}
}


