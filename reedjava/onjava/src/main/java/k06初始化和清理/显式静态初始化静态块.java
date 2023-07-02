/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈静态代码块  只会在第一次创建和第一次访问该类时创建  后续创建和访问不会再执行
 * 只要访问就会创建,即使没有创建过该类的对象
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/23 18:15
 * reedsource@189.cn
 */
public class 显式静态初始化静态块 {
    static Cups cups1 = new Cups();  // [2]

    public static void main(String[] args) {
        DealLog.log("Inside main()");
        Cups.cup1.f(99);     // [1]

        DealLog.log(cups1);
        DealLog.log(cups2);

		/*
		通过[1] 和 [2] 都会使 Cups 中的 static静态初始化发生
		如果同时注释 将不会发生

		方式[2]无论执行几次  静态初始化都只会执行一次
		*/
    }

    static Cups cups2 = new Cups();  // [2]

}

class Cup {
    Cup(int marker) {
        DealLog.log("Cup(" + marker + ")");
    }

    void f(int marker) {
        DealLog.log("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    Cups() {
        DealLog.log("Cups()");
    }
}