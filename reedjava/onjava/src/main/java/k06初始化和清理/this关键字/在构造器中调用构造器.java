/*
 * Author:   reedsource
 */
package k06初始化和清理.this关键字;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/25 20:54
 * reedsource@189.cn
 */
public class 在构造器中调用构造器 {
    int 构造器int = 0;
    String s = "构造器初始String值";

    在构造器中调用构造器(int petals) {
        构造器int = petals;
        DealLog.log("由构造器单int参数构造器输出", 构造器int);
    }

    在构造器中调用构造器(String ss) {
        DealLog.log("由构造器单String参数构造器输出 ", ss);
        s = ss;
    }

    在构造器中调用构造器(String s, int petals) {
        //将传入构造器的值 调用另一个构造器
        this(petals);
        //2. 不能同时调用两个构造器,编译器报错
        //this(s);

        // this的另一种用法,避免同名带来的歧义
        this.s = s;
        DealLog.log("由构造器String + int 双参数构造器输出");
    }

    在构造器中调用构造器() {
        //1. 在构造器中调用构造器,必须在构造器的最开始部分,否则编译器报错
        this("构造器String变更值", 47);
        DealLog.log("由无参数构造器输出");
    }

    void printPetalCount() {
        //3. 不能在非构造器里调用构造器 ,编译器报错
        // this(11);
        DealLog.log("构造器int = " + 构造器int + " s = " + s);
    }

    public static void main(String[] args) {
        在构造器中调用构造器 x = new 在构造器中调用构造器();
        x.printPetalCount();

        //由构造器单int参数构造器输出 47
        //由构造器String + int 双参数构造器输出
        //由无参数构造器输出
        //构造器int = 47 s = 构造器String变更值
    }

}
