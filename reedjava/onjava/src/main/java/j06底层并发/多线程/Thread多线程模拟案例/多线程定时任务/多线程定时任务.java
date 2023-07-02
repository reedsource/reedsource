/*
 * FileName: 多线程定时任务
 * Author:   reedsource
 */
package j06底层并发.多线程.Thread多线程模拟案例.多线程定时任务;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 功能简述:
 * 〈多线程定时任务〉
 * 可以在指定的时间执行某个任务
 * 每隔一段时间就重复执行某个任务
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 多线程定时任务 {
    @Test
    public void scheduleThreadPool() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        定时任务 t1 = new 定时任务();
        //立即执行t1，3s后任务结束，再等待2s（间隔时间-消耗时间），如果有空余线程时，再次执行该任务
        pool.scheduleAtFixedRate(t1, 0, 5, TimeUnit.SECONDS);
    }
}

/**
 * 定时任务
 */
class 定时任务 implements Runnable {

    @Override
    public void run() {
        DealLog.log("----定时任务开始--------" + new Date().toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DealLog.log("----定时任务执行3秒, 定时任务结束--------" + new Date().toString());
    }
}
