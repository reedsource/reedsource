/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理.构造器保证初始化与重载;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/22 16:29
 * reedsource@189.cn
 */
public class 构造对象 {

    //构造器的名称就是类的名字.保证名称不会冲突  和 保证类命的默认,让编译器知道调用那个构造器

    //构造器是特殊方法,没有返回类型
    //也可以说是返回了新建对象的引用

    //无参数构造器
    //当新建构造对象没有构造函数时,系统一般会自动创建一个无参构造函数
    //如果已经有了构造函数,就不会自动创建了
    //实际 一般建议必须有一个无参构造函数,避免异常问题
    public 构造对象() {
        DealLog.log("无参构造函数,被构造时打印");
    }

    //有参构造器
    public 构造对象(int m) {
        DealLog.logTo("有参数构造 参数", m);
    }

    //以上有相同名称的构造函数,以应对不同的需求 就是重载
    //每个重载的方法名称相同,但是参数列表必须不同,能让编译器区分
    //------------------------------------------------------------------------

    public int 构造对象(int m) {
        DealLog.logTo("有参数构造 参数", m);
        return m + 1;
    }

    //这种不是构造方法,实际相当于公共方法
    //无法直接用来构建,只能在构建完成的实体中调用
}
