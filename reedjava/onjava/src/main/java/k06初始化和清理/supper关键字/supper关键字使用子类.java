/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理.supper关键字;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/4 13:49
 * reedsource@189.cn
 */
public class supper关键字使用子类 extends supper父类 {


    String msg = "子类方法的msg";


    /**
     * super关键字的用法有三种
     * 1.在子类的成员方法中，访问父类的成员变量
     */
    public supper关键字使用子类() {
        super();
    }

    /**
     * 2.在子类的成员方法中，访问父类的成员方法。
     */
    public void methodZi() {
        //父类方法的msg
        DealLog.log(super.msg);
    }

    /**
     * 3.在子类的构造方法中，访问父类的构造方法。
     */
    public void method() {
        //访问父类中的method
        super.method();
        DealLog.log("子类方法method");
    }

    @Test
    public void supper关键字使用子类_Test() {
        method();
        methodZi();
        DealLog.log(msg);
    }

}
