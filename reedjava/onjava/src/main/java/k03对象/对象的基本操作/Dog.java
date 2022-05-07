/*
 * FileName: Dog
 * Author:   reedbook
 */
package k03对象.对象的基本操作;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈小狗〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/6 22:56
 * reedsource@189.cn
 */
public class Dog {

	int dogAge;

	/*通过重写get set方法使类的属性暴露出来*/
	public int getDogAge() {
		return dogAge;
	}

	public void setDogAge(int dogAge) {
		this.dogAge = dogAge;
	}

	//Java编译器将会为该类提供一个默认构造方法
	public Dog(){
	}

	/*通过构造方法,使外部通过构造方法直接生成实体类*/
	public Dog(String name){
		//这个构造器仅有一个参数：name
		DealLog.log("小狗的名字是 : " + name );
	}
}
/*
一个源文件中只能有一个public类
一个源文件可以有多个非public类
源文件的名称应该和public类的类名保持一致。例如：源文件中public类的类名是Employee，那么源文件应该命名为Employee.java。
如果一个类定义在某个包中，那么package语句应该在源文件的首行。
如果源文件包含import语句，那么应该放在package语句和类定义之间。如果没有package语句，那么import语句应该在源文件中最前面。
import语句和package语句对源文件中定义的所有类都有效。在同一源文件中，不能给不同的类不同的包声明。
*/