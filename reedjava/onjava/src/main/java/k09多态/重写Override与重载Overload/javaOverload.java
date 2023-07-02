/*
 * FileName: javaOverload
 * Author:   reedsource
 */
package main.java.k09多态.重写Override与重载Overload;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java重载〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/10 20:16
 * reedsource@189.cn
 * <p>
 * 重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。
 * 每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。
 * 最常用的地方就是构造器的重载。
 * <p>
 * 重载规则:
 * <p>
 * 被重载的方法必须改变参数列表(参数个数或类型不一样)；
 * 被重载的方法可以改变返回类型；
 * 被重载的方法可以改变访问修饰符；
 * 被重载的方法可以声明新的或更广的检查异常；
 * 方法能够在同一个类中或者在一个子类中被重载。
 * 无法以返回值类型作为重载函数的区分标准
 */
public class javaOverload {
    public static void main(String[] args) {
        javaOverload o = new javaOverload();
        DealLog.log(o.test());
        o.test(1);
        DealLog.log(o.test(1, "test3"));
        DealLog.log(o.test("test4", 1));
    }

    public int test() {
        DealLog.log("test1");
        return 1;
    }

    public void test(int a) {
        DealLog.log("test2");
    }

    //以下两个参数类型顺序不同
    public String test(int a, String s) {
        DealLog.log("test3");
        return "returntest3";
    }

    public String test(String s, int a) {
        DealLog.log("test4");
        return "returntest4";
    }

}
