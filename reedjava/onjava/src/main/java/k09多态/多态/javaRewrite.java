/*
 * FileName: javaRewrite
 * Author:   reedsource
 */
package main.java.k09多态.多态;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈重写〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/10 20:16
 * reedsource@189.cn
 */
public class javaRewrite {
    public static void main(String[] args) {
        Salary s = new Salary("员工 A", "北京", 3, 3600.00);
        Employee e = new Salary("员工 B", "上海", 2, 2400.00);
        DealLog.log("使用 Salary 的引用调用 mailCheck -- ");
        s.mailCheck();
        DealLog.log("\n使用 Employee 的引用调用 mailCheck--");
        e.mailCheck();
    }
}

/*
实例中，实例化了两个 Salary 对象：一个使用 Salary 引用 s，另一个使用 Employee 引用 e。
当调用 s.mailCheck() 时，编译器在编译时会在 Salary 类中找到 mailCheck()，执行过程 JVM 就调用 Salary 类的 mailCheck()。
因为 e 是 Employee 的引用，所以调用 e 的 mailCheck() 方法时，编译器会去 Employee 类查找 mailCheck() 方法 。
在编译的时候，编译器使用 Employee 类中的 mailCheck() 方法验证该语句， 但是在运行的时候，Java虚拟机(JVM)调用的是 Salary 类中的 mailCheck() 方法。
以上整个过程被称为虚拟方法调用，该方法被称为虚拟方法。
Java中所有的方法都能以这种方式表现，因此，重写的方法能在运行时调用，不管编译的时候源代码中引用变量是什么数据类型。
*/

