/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/23 18:31
 * reedsource@189.cn
 */
public class 非静态实例初始化 {
    Mug mug1;
    Mug mug2;

    {                                         // [1]
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        System.out.println("mug1 & mug2 初始化");
    }

    非静态实例初始化() {
        System.out.println("非静态实例初始化()");
    }

    非静态实例初始化(int i) {
        System.out.println("非静态实例初始化(int)");
    }

    public static void main(String[] args) {
        System.out.println("main()方法内");
        new 非静态实例初始化();
        System.out.println("new 非静态实例初始化() 后");
        new 非静态实例初始化(1);
        System.out.println("new 非静态实例初始化(1) 后");

        //实例初始化子句在构造器之前执行

        //main()方法内
        //非静态实例初始化(1)
        //非静态实例初始化(2)
        //mug1 & mug2 初始化
        //非静态实例初始化()
        //new 非静态实例初始化() 后
        //非静态实例初始化(1)
        //非静态实例初始化(2)
        //mug1 & mug2 初始化
        //非静态实例初始化(int)
        //new 非静态实例初始化(1) 后
    }
}

class Mug {
    Mug(int marker) {
        System.out.println("非静态实例初始化(" + marker + ")");
    }
}