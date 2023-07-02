/*
 * FileName: 四种线程池与自定义线程池
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 功能简述:
 * 〈四种线程池与自定义线程池
 * 线程池任务执行流程：
 * <p>
 * 当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
 * 当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
 * 当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
 * 当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
 * 当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
 * 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
 * <p>
 * 一般如果线程池任务队列采用LinkedBlockingQueue队列的话，那么不会拒绝任何任务（因为队列大小没有限制），这种情况下，ThreadPoolExecutor最多仅会按照最小线程数来创建线程，也就是说线程池大小被忽略了。如果线程池任务队列采用
 * ArrayBlockingQueue队列的话，那么ThreadPoolExecutor将会采取一个非常负责的算法，比如假定线程池的最小线程数为4，最大为8所用的ArrayBlockingQueue最大为10。随着任务到达并被放到队列中，线程池中最多运行4个线程（即最小线程数）。
 * 即使队列完全填满，也就是说有10个处于等待状态的任务，ThreadPoolExecutor也只会利用4个线程。如果队列已满，而又有新任务进来，此时才会启动一个新线程，这里不会因为队列已满而拒接该任务，相反会启动一个新线程。新线程会运行队列中的
 * 第一个任务，为新来的任务腾出空间。这个算法背后的理念是：该池大部分时间仅使用核心线程（4个），即使有适量的任务在队列中等待运行。这时线程池就可以用作节流阀。如果挤压的请求变得非常多，这时该池就会尝试运行更多的线程来清理；
 * 这时第二个节流阀—最大线程数就起作用了。
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/19 16:58
 * reedsource@189.cn
 */
public class 四种线程池与自定义线程池 {

    // 线程池中的常驻核心线程数
    private static final int CORE_POOL_SIZE = 8;

    // 线程池能够容纳同时执行的最大线程数
    private static final int MAXIMUM_POOL_SIZE = 16;


    /**
     * newCachedThreadPool
     * <p>
     * 底层：返回ThreadPoolExecutor实例，corePoolSize为0；maximumPoolSize为Integer.MAX_VALUE；keepAliveTime为60L；unit为TimeUnit.SECONDS；workQueue为SynchronousQueue(同步队列)
     * 通俗：当有新任务到来，则插入到SynchronousQueue中，由于SynchronousQueue是同步队列，因此会在池中寻找可用线程来执行，若有可以线程则执行，若没有可用线程则创建一个线程来执行该任务；若池中线程空闲时间超过指定大小，则该线程会被销毁。
     * 适用：执行很多短期异步的小程序或者负载较轻的服务器
     * <p>
     * 1.创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程<br>
     * 2.当任务数增加时，此线程池又可以智能的添加新线程来处理任务<br>
     * 3.此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小<br>
     */
    @Test
    public void cacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int m = i;
            try {
                Thread.sleep(m);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行" + m));
        }
    }


    /**
     * newFixedThreadPool：
     * <p>
     * 底层：返回ThreadPoolExecutor实例，接收参数为所设定线程数量nThread，corePoolSize为nThread，maximumPoolSize为nThread；keepAliveTime为0L(不限时)；unit为：TimeUnit.MILLISECONDS；WorkQueue为：new LinkedBlockingQueue<Runnable>() 无界阻塞队列
     * 通俗：创建可容纳固定数量线程的池子，每隔线程的存活时间是无限的，当池子满了就不在添加线程了；如果池中的所有线程均在繁忙状态，对于新任务会进入阻塞队列中(无界的阻塞队列)
     * 适用：执行长期的任务，性能好很多
     * <p>
     * 1.创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小<br>
     * 2.线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程<br>
     * 3.因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字，和线程名称<br>
     */
    @Test
    public void fixTheadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int ii = i;
            fixedThreadPool.execute(() -> {
                System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行" + ii);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * newSingleThreadExecutor:
     * <p>
     * 底层：FinalizableDelegatedExecutorService包装的ThreadPoolExecutor实例，corePoolSize为1；maximumPoolSize为1；keepAliveTime为0L；unit为：TimeUnit.MILLISECONDS；workQueue为：new LinkedBlockingQueue<Runnable>() 无解阻塞队列
     * 通俗：创建只有一个线程的线程池，且线程的存活时间是无限的；当该线程正繁忙时，对于新任务会进入阻塞队列中(无界的阻塞队列)
     * 适用：一个任务一个任务执行的场景
     * <p>
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    @Test
    public void singleTheadPoolTest() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int ii = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + "=>" + ii));
        }
    }

    /**
     * NewScheduledThreadPool:
     * <p>
     * 底层：创建ScheduledThreadPoolExecutor实例，corePoolSize为传递来的参数，maximumPoolSize为Integer.MAX_VALUE；keepAliveTime为0；unit为：TimeUnit.NANOSECONDS；workQueue为：new DelayedWorkQueue() 一个按超时时间升序排序的队列
     * 通俗：创建一个固定大小的线程池，线程池内线程存活时间无限制，线程池可以支持定时及周期性任务执行，如果所有线程均处于繁忙状态，对于新任务会进入DelayedWorkQueue队列中，这是一种按照超时时间排序的队列结构
     * 适用：周期性执行任务的场景
     * <p>
     * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行
     */
    @Test
    public void scheduleThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        Runnable r1 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:3秒后执行");
        scheduledThreadPool.schedule(r1, 3, TimeUnit.SECONDS);
        Runnable r2 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:延迟2秒后每3秒执行一次");
        scheduledThreadPool.scheduleAtFixedRate(r2, 2, 3, TimeUnit.SECONDS);
        Runnable r3 = () -> System.out.println("线程名称：" + Thread.currentThread().getName() + "，执行:普通任务");
        for (int i = 0; i < 5; i++) {
            scheduledThreadPool.execute(r3);
        }
    }


    /*
     * 自定义线程池
     */
    @Test
    public void ThreadPool() throws InterruptedException {
        // 使用 ThreadFactoryBuilder 创建自定义线程名称的 ThreadFactory
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("hyn-demo-pool-%d").build();

        // 创建线程池，其中任务队列需要结合实际情况设置合理的容量
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                //corePoolSize 线程池中的常驻核心线程数
                //在创建了线程池后，当有请求任务进来之后，就会安排池中的线程去执行请求任务，近似理解为今日当值线程
                //当线程池中的线程数目达到 corePoolSize 后，就会把到达的任务放入缓存队列中
                CORE_POOL_SIZE,
                //maximumPoolSize 线程池能够容纳同时执行的最大线程数，此值必须大于等于1
                MAXIMUM_POOL_SIZE,
                //keepAliveTime 多余的空闲线程的存活时间
                //当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，
                //多余空闲线程会被销毁直到只剩下 corePoolSize 个线程为止
                //默认情况下，只有当线程池中的线程数大于 corePoolSize 时，keepAliveTime才会起作用，直到线程池找那个的线程数不大于corePoolSize。
                0L,
                // keepAliveTime的单位
                TimeUnit.MILLISECONDS,
                // workQueue 任务队列，被提交但尚未被执行的任务
                new LinkedBlockingQueue<>(1024),
                // threadFactory 表示生成线程池中工作线程的线程工厂，用于创建线程一般默认即可
                namedThreadFactory,
                //handler 拒绝策略，表示当队列满了并且工作线程大于等于线程池最大线程数（maximumPoolSize）时如何处理
                new ThreadPoolExecutor.AbortPolicy());

        // 新建 1000 个任务，每个任务是打印当前线程名称
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }


        //以下 线程池的关闭方式

		/* 1, shutdown()
		将线程池状态置为SHUTDOWN,并不会立即停止：
		停止接收外部submit的任务
		内部已经提交，会执行完  (已经提交的任务会分两类：一类是已经在执行的，另一类是还没有开始执行的)
		等到第二步完成后，才真正停止*/
        executor.shutdown();

		/* 2.shutdownNow()
		将线程池状态置为STOP。企图立即停止，事实上不一定：
		跟shutdown()一样，先停止接收外部提交的任务
		忽略队列里等待的任务
		尝试将正在跑的任务interrupt中断
		返回未执行的任务列表

		它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，但是大家知道，这种方法的作用有限，如果线程中没有sleep 、wait、Condition、定时锁等应用,
		interrupt()方法是无法中断当前的线程的。
		所以，ShutdownNow()并不代表线程池就一定立即就能退出，它也可能必须要等待所有正在执行的任务都执行完成了才能退出。
        但是大多数时候是能立即退出的*/
        executor.shutdownNow();


		/* 3 ,awaitTermination(long timeOut, TimeUnit unit)
		awaitTermination(超时时间, 时间单位)

		这个方法会使线程等待 超时时间 时长，当超过 超时时间 后，会监测ExecutorService是否已经关闭，若关闭则返回true，否则zhi返回false。
		一般情况下会和shutdown方法组合使用

		awaitTermination不会关闭ExecutorService，只是定时检测一下他是否关闭*/

        executor.shutdown();
        while (!executor.awaitTermination(100L, TimeUnit.SECONDS)) {
            System.out.println("线程池未关闭");
        }
        System.out.println("全部线程关闭");


		/*shutdown()和shutdownNow()的区别

		从字面意思就能理解，shutdownNow()能立即停止线程池，正在跑的和正在等待的任务都停下了。这样做立即生效，但是风险也比较大；
		shutdown()只是关闭了提交通道，用submit()是无效的；而内部该怎么跑还是怎么跑，跑完再停。*/

		/*shutdown()和awaitTermination()的区别

		shutdown()后，不能再提交新的任务进去；但是awaitTermination()后，可以继续提交。
		awaitTermination()是阻塞的，返回结果是线程池是否已停止（true/false）；shutdown()不阻塞。*/


		/* 总结
		优雅的关闭，用shutdown()
		想立马关闭，并得到未执行任务列表，用shutdownNow()

		关闭功能 【从强到弱】 依次是：shutdownNow() > shutdown() > awaitTermination()*/

        // 任务执行完毕后打印"Done"
        System.out.println("Done");
    }
}
