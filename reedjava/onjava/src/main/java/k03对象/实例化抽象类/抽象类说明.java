/*
 * FileName: 抽象类说明
 * Author:   reedsource
 */
package main.java.k03对象.实例化抽象类;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈抽象类说明〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/10 20:16
 * reedsource@189.cn
 * <p>
 * 在面向对象的概念中，所有的对象都是通过类来描绘的，但是反过来，
 * 并不是所有的类都是用来描绘对象的，如果一个类中没有包含足够的信息来描绘一个具体的对象，这样的类就是抽象类。
 * 抽象类除了不能实例化对象之外，类的其它功能依然存在，成员变量、成员方法和构造方法的访问方式和普通类一样。
 * 由于抽象类不能实例化对象，所以抽象类必须被继承，才能被使用。也是因为这个原因，通常在设计阶段决定要不要设计抽象类。
 * 父类包含了子类集合的常见的方法，但是由于父类本身是抽象的，所以不能使用这些方法。
 */
public class 抽象类说明 {
    public static void main(String[] args) {
        /* 以下是不允许的，会引发错误 */
        //抽象基类 e = new 抽象基类("George W.", "Houston, TX", 43);

        DealLog.log("\n Call mailCheck using 抽象基类 reference--");
        //e.mailCheck();
    }
}

/*
抽象方法
如果你想设计这样一个类，该类包含一个特别的成员方法，该方法的具体实现由它的子类确定，那么你可以在父类中声明该方法为抽象方法。
Abstract 关键字同样可以用来声明抽象方法，抽象方法只包含一个方法名，而没有方法体。
抽象方法没有定义，方法名后面直接跟一个分号，而不是花括号。

public abstract class 抽象基类
{
   private String name;
   private String address;
   private int number;

   public abstract double computePay();

   //其余代码
}

声明抽象方法会造成以下两个结果：

如果一个类包含抽象方法，那么该类必须是抽象类。
任何子类必须重写父类的抽象方法，或者声明自身为抽象类。
继承抽象方法的子类必须重写该方法。
否则，该子类也必须声明为抽象类。
最终，必须有子类实现该抽象方法，否则，从最初的父类到最终的子类都不能用来实例化对象。

如果Salary类继承了Employee类，那么它必须实现computePay()方法：

public class 继承抽象基类 extends 抽象基类
{
	private double salary; // Annual salary

	public double computePay()
	{
		DealLog.log("Computing salary pay for " + getName());
		return salary/52;
	}

	//其余代码
}

抽象类总结规定
1. 抽象类不能被实例化(初学者很容易犯的错)，如果被实例化，就会报错，编译无法通过。只有抽象类的非抽象子类可以创建对象。
2. 抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。
3. 抽象类中的抽象方法只是声明，不包含方法体，就是不给出方法的具体实现也就是方法的具体功能。
4. 构造方法，类方法（用 static 修饰的方法）不能声明为抽象方法。
5. 抽象类的子类必须给出抽象类中的抽象方法的具体实现，除非该子类也是抽象类。
*/


