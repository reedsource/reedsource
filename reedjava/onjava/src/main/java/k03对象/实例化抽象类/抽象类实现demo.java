/*
 * FileName: 抽象类实现demo
 * Author:   reedsource
 */
package k03对象.实例化抽象类;

import top.ireed.deal.DealLog;

/**
 * 尽管我们不能实例化一个 抽象基类 类的对象，
 * 但是如果我们实例化一个 继承抽象基类 类对象，
 * 该对象将从 抽象基类 类继承 7 个成员方法，
 * 且通过该方法可以设置或获取三个成员变量。
 */
class 抽象类实现demo {
    public static void main(String[] args) {
        继承抽象基类 s = new 继承抽象基类("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
        抽象基类 e = new 继承抽象基类("John Adams", "Boston, MA", 2, 2400.00);

        DealLog.log("Call mailCheck using 继承抽象基类 reference --");
        s.mailCheck();

        DealLog.log("\n Call mailCheck using 抽象基类 reference--");
        e.mailCheck();

        //Constructing an 抽象基类
        //Constructing an 抽象基类
        //Call mailCheck using  继承抽象基类 reference --
        //Within mailCheck of 继承抽象基类 class
        //Mailing check to Mohd Mohtashim with salary 3600.0
        //
        //Call mailCheck using 抽象基类 reference--
        //Within mailCheck of 继承抽象基类 class
        //Mailing check to John Adams with salary 2400.
    }
}
