/*
 * Author:   reedsource
 */
package k06初始化和清理.this关键字;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈super关键字用来访问父类内容，而this 关键字用来访问本类内容，用法也有三种：
 * <p>
 * 在第三种用法中要注意：
 * a. this(..)调用也必须是构造方法的第一语句，唯一一个
 * b.super和this两种构造调用，不能同时使用。〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/23 9:41
 * reedsource@189.cn
 */
public class this简单使用 {
    int i;

    private this简单使用 increment() {
        i++;
        //这里的this返回了当前对象的引用
        return this;
    }

    private void print() {
        //2.在本类的成员方法中，访问本类的另一个成员方法。
        this.print2();
    }

    private void print2() {
        //1.在本类的成员方法中，访问本类的成员变量。
        DealLog.log("i = ", this.i);
    }

    public this简单使用() {
        // 3.在本类的构造方法中，访问本类的另一个构造方法。
        this(10);
    }

    public this简单使用(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        this简单使用 x = new this简单使用();
        x.increment().increment().increment().print();
    }


    //i = 3
}
