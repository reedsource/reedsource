/*
 * FileName: K8001Test
 * Author:   reedsource
 */
package k03对象.对象的基本操作;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈实现对象实体创建调用〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/6 22:56
 * reedsource@189.cn
 */
public class K03 {
	public static void main(String[] args) {
		// 下面的语句将创建一个对象
		Dog dog = new Dog();

		DealLog.log(dog);
		//未重写toString()方法时 将会直接打印类地址
		//k03对象.对象的基本操作.Dog@223191a6

		//使用构造函数创建有初始值的对象
		dog = new Dog("tommy");
		/* 通过方法来设定age */
		dog.setDogAge(11);
		/* 调用另一个方法获取age */
		int a = dog.getDogAge();
		DealLog.log(a);
		/*你也可以像下面这样访问成员变量 */
		//也可以
		DealLog.log("变量值 : ", dog.getDogAge());

		/**
		 * 错误写法
		 * Dog dog = null;
		 * dog.setDogAge(111);
		 * */
	}
}
