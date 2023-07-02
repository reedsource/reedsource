/*
 * FileName: 多线程生产者消费者
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程.Thread多线程模拟案例.多线程生产者消费者;

import top.ireed.deal.DealLog;

import java.util.LinkedList;

import static top.ireed.general.TopConstant.INT10;
import static top.ireed.general.TopConstant.INT30;

/**
 * 功能简述:
 * 〈多线程生产者消费者问题〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程生产者消费者 {
    public static void main(String[] args) {
        //创建仓库
        仓库 仓库 = new 仓库();
        //生产者
        生产者 生产者 = new 生产者(仓库);
        //消费者
        消费者 消费者 = new 消费者(仓库);

        生产者.setName("生产者");
        消费者.setName("消费者");

        生产者.start();
        消费者.start();
    }
}

/**
 * 仓库类
 */
class 仓库 {
    /**
     * 仓库产品
     */
    private LinkedList<Object> list = new LinkedList<>();
    /**
     * 仓库最大值
     */
    private static final int MAX = 100;

    /**
     * 存入, 约定每次存10个产品
     */

    synchronized void store() {
        //如果仓库已满, 等待消费者消费
        while (list.size() >= MAX) {
            try {
                this.wait();        //wait()/notify()需要在同步代码块中,使用锁对象调用
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        DealLog.log(Thread.currentThread().getName() + "存储前,仓库中产品数量:" + list.size());
        for (int i = 1; i <= INT10; i++) {
            list.offer(new Object());
        }
        DealLog.log(Thread.currentThread().getName() + "存储了10个产品,仓库中产品数量:" + list.size());
        //通知消费者消费
        this.notify();
    }

    /**
     * 取出,约定一次取10个产品
     */
    synchronized void take() {
        //如果仓库已空, 等待
        while (list.size() <= 0) {
            try {
                this.wait();        //会释放锁对象
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DealLog.log(Thread.currentThread().getName() + "消费前,仓库中产品数量:" + list.size());
        for (int i = 1; i <= INT10; i++) {
            list.poll();
        }
        DealLog.log(Thread.currentThread().getName() + "消费10个产品,仓库中产品数量:" + list.size());
        //通知生产者生产
        this.notify();
    }
}

/**
 * 生产者类
 */
class 生产者 extends Thread {
    private 仓库 storage;

    生产者(仓库 storage) {
        super();
        this.storage = storage;
    }

    @Override
    public void run() {
        //向仓库中存储30次
        for (int x = 1; x <= INT30; x++) {
            storage.store();
        }
    }
}

/**
 * 消费者类
 */
class 消费者 extends Thread {
    /**
     * 仓库
     */
    private 仓库 storage;

    消费者(仓库 storage) {
        super();
        this.storage = storage;
    }

    @Override
    public void run() {
        //从仓库中取30次
        for (int x = 1; x <= INT30; x++) {
            storage.take();
        }
    }
}
