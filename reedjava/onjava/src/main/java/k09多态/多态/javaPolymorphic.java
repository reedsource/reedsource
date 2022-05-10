/*
 * FileName: javaPolymorphic
 * Author:   reedsource
 */
package k09多态.多态;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java多态〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/10 20:16
 * reedsource@189.cn
 *
 多态的优点
 1. 消除类型之间的耦合关系
 2. 可替换性
 3. 可扩充性
 4. 接口性
 5. 灵活性
 6. 简化性

 多态存在的三个必要条件
 继承
 重写
 父类引用指向子类对象

 当使用多态方式调用方法时，首先检查父类中是否有该方法，如果没有，则编译错误；
 如果有，再去调用子类的同名方法。

 多态的好处：可以使程序有良好的扩展，并可以对所有类的对象进行通用处理。

 */
public class javaPolymorphic {
	public static void main(String[] args) {
		show(new Cat());  // 以 Cat 对象调用 show 方法
		show(new Dog());  // 以 Dog 对象调用 show 方法

		Animal a = new Cat();  // 向上转型
		a.eat();               // 调用的是 Cat 的 eat
		Cat c = (Cat)a;        // 向下转型
		c.work();        // 调用的是 Cat 的 work
	}

	public static void show(Animal a)  {
		a.eat();
		// 类型判断
		if (a instanceof Cat)  {  // 猫做的事情
			Cat c = (Cat)a;
			c.work();
		} else if (a instanceof Dog) { // 狗做的事情
			Dog c = (Dog)a;
			c.work();
		}
	}

	//吃鱼
	//抓老鼠
	//吃骨头
	//看家
	//吃鱼
	//抓老鼠
}

abstract class Animal {
	abstract void eat();
}

class Cat extends Animal {
	@Override
	public void eat() {
		DealLog.log("吃鱼");
	}
	public void work() {
		DealLog.log("抓老鼠");
	}
}

class Dog extends Animal {
	@Override
	public void eat() {
		DealLog.log("吃骨头");
	}
	public void work() {
		DealLog.log("看家");
	}
}