/*
 * FileName: 创建线程实现Callable接口有返回值
 * Author:   reedsource
 */
package main.java.j06底层并发.多线程;

import top.ireed.deal.DealLog;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static top.ireed.general.TopConstant.INT100;

/**
 * 功能简述:
 * 〈创建线程方式三, 实现Callable接口 实现有返回值的多线程程序
 * Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，此方法会阻塞线程直到获取“将来”的结果，
 * 当不调用此方法时，主线程不会阻塞
 * Callable接口实现类中run()方法允许将异常向上抛出，也可以直接在内部处理(try...catch);〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 创建线程实现Callable接口有返回值 {

    public static void main(String[] args) {
        //3)实例化任务，传递参数
        MyTask myTask = new MyTask("11", "22");
        //4)将任务放进FutureTask里
        FutureTask<Object> futureTask = new FutureTask<>(myTask);
        //采用thread来开启多线程，futuretask继承了Runnable，可以放在线程池中来启动执行
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            //5)获取子线程的执行结果
            //get():获取任务执行结果，如果任务还没完成则会阻塞等待直到任务执行完成。如果任务被取消则会抛出CancellationException异常，
            //如果任务执行过程发生异常则会抛出ExecutionException异常，如果阻塞等待过程中被中断则会抛出InterruptedException异常。
            boolean result = (boolean) futureTask.get();
            DealLog.log(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 1) 定义一个类实现Callable接口, 通过泛型指定call()方法返回值类型
 */
class MyTask implements Callable<Object> {
    private String args1;
    private String args2;

    /**
     * 构造函数，用来向task中传递任务的参数
     *
     * @param args1 参数1
     * @param args2 参数2
     */
    MyTask(String args1, String args2) {
        this.args1 = args1;
        this.args2 = args2;
    }

    /**
     * 2) 重写call()方法
     *
     * @return 返回数据
     */
    @Override
    public Object call() {
        for (int i = 0; i < INT100; i++) {
            DealLog.log(args1 + args2 + i);
        }
        return true;
    }
}