/*
 * FileName: javaMethod
 * Author:   reedbook
 */
package k06初始化和清理;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java方法,继承,传递参数,重载,构造方法〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class javaMethod {
	public static void main(String[] args) {
		//方法调用
		//Java 支持两种调用方法的方式，根据方法是否返回值来选择。
		//当程序调用一个方法时，程序的控制权交给了被调用的方法。
		//当被调用方法的返回语句执行或者到达方法体闭括号时候交还控制权给程序。
		//当方法返回一个值的时候，方法调用通常被当做一个值

		//如果方法返回值是void，方法调用一定是一条语句。
		DealLog.log(c0(12, 15));

		//一个void方法的调用一定是一个语句。
		//所以，它被在main方法第三行以语句形式调用。就像任何以分号结束的语句一样。


		int num1 = 1;
		int num2 = 2;
		DealLog.log("交换前 num1 的值为：" + num1 + " ，num2 的值为：" + num2);
		// 调用c1方法
		c1(num1, num2);
		DealLog.log("交换后 num1 的值为：" + num1 + " ，num2 的值为：" + num2);


		double m = 1.11;
		double n = 2.22;

		//调用重载后的c1
		c1(m, n);

		DealLog.log();

		//调用构造方法来初始化一个对象
		MyClass t1 = new MyClass();
		MyClass t2 = new MyClass();
		DealLog.log(t1.x + " " + t2.x);

		//10 10

		DealLog.log();

		c2(1.1, 2.4, 3.3);

		DealLog.log("------------------------");

		Cake c1 = new Cake(1);
		Cake c2 = new Cake(2);
		Cake c3 = new Cake(3);

		c2 = c3 = null;
		//调用Java垃圾收集器
		System.gc();
	}


	/**
	 * 返回两个整型变量数据的较大值
	 *
	 * @param num1 值1
	 * @param num2 值2
	 * @return 最大的值
	 * <p>
	 * 方法是解决一类问题的步骤的有序组合
	 * 方法包含于类或对象中
	 * 方法在程序中被创建，在其他地方被引用
	 * <p>
	 * 方法构成
	 * <p>
	 * 修饰符：修饰符，这是可选的，告诉编译器如何调用该方法。定义了该方法的访问类型。
	 * 返回值类型 ：方法可能会返回值。returnValueType 是方法返回值的数据类型。有些方法执行所需的操作，但没有返回值。在这种情况下，returnValueType 是关键字void。
	 * 方法名：是方法的实际名称。方法名和参数表共同构成方法签名。
	 * 参数类型：参数像是一个占位符。当方法被调用时，传递值给参数。这个值被称为实参或变量。参数列表是指方法的参数类型、顺序和参数的个数。参数是可选的，方法可以不包含任何参数。
	 * 方法体：方法体包含具体的语句，定义该方法的功能。
	 */
	private static int c0(int num1, int num2) {
		int result;
		if (num1 > num2) {
			result = num1;
		} else {
			result = num2;
		}

		return result;
	}


	/**
	 * 参数交换
	 *
	 * @param n1 参数1
	 * @param n2 参数2
	 * @return
	 */
	private static void c1(int n1, int n2) {
		DealLog.log("\t进入 swap 方法");
		DealLog.log("\t\t交换前 n1 的值为：" + n1
				+ "，n2 的值：" + n2);
		// 交换 n1 与 n2的值
		int temp = n1;
		n1 = n2;
		n2 = temp;

		DealLog.log("\t\t交换后 n1 的值为 " + n1 + "，n2 的值：" + n2);

		//交换前 num1 的值为：1 ，num2 的值为：2
		//	进入 swap 方法
		//		交换前 n1 的值为：1，n2 的值：2
		//		交换后 n1 的值为 2，n2 的值：1
		//交换后 num1 的值为：1 ，num2 的值为：2
	}

	/**
	 * 浮点参数最大值
	 * <p>
	 * 基于java重载
	 * 创建另一个有相同名字但参数不同的方法
	 * 就是说一个类的两个方法拥有相同的名字，但是有不同的参数列表
	 *
	 * @param num1 参数1
	 * @param num2 参数2
	 * @return
	 */
	private static double c1(double num1, double num2) {
		if (num1 > num2) {
			return num1;
		} else {
			return num2;
		}
	}

	/*
	变量作用域

	变量的范围是程序中该变量可以被引用的部分。
	方法内定义的变量被称为局部变量。
	局部变量的作用范围从声明开始，直到包含它的块结束。
	局部变量必须声明才可以使用。
	方法的参数范围涵盖整个方法。参数实际上是一个局部变量。
	for循环的初始化部分声明的变量，其作用范围在整个循环。
	但循环体内声明的变量其适用范围是从它声明到循环体结束

	你可以在一个方法里，不同的非嵌套块中多次声明一个具有相同的名称局部变量，但你不能在嵌套块内两次声明局部变量
	 */

	/*
	命令行参数的使用

	使用javac 运行程序后
	可以通过java 程序名称 +参数 向main方法传递参数

	public class CommandLine {
    public static void main(String args[]){
	      for(int i=0; i<args.length; i++){
	         DealLog.log("args[" + i + "]: " + args[i]);
	      }
       }
	}

	//$ javac CommandLine.java
	//$ java CommandLine this is a command line 200 -100
	//args[0]: this
	//args[1]: is
	//args[2]: a
	//args[3]: command
	//args[4]: line
	//args[5]: 200
	//args[6]: -100

	* */

	/**
	 * 变长参数 判断最大值
	 * 一个函数至多只能有一个可变参数，且可变参数为最后一个参数。
	 * 对于可变参数，编译器会将其转型为一个数组，故在函数内部，可变参数名即可看作数组名
	 * <p>
	 * 优先匹配固定参数的方法
	 *
	 * @param numbers 变长参数
	 */
	private static void c2(double... numbers) {
		if (numbers.length == 0) {
			DealLog.log("没有参数传递");
			return;
		}

		double result = numbers[0];

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > result) {
				result = numbers[i];
			}
		}
		DealLog.log("最大值为 " + result);
	}

}

/*
构造方法
*/
// 一个简单的构造函数
class MyClass {
	int x;

	// 以下是构造函数
	MyClass() {
		x = 10;
	}
}


/**
 * finalize() 方法 清除回收对象
 * <p>
 * 关键字 protected 是一个限定符，它确保 finalize() 方法不会被该类以外的代码调用。
 * <p>
 * 当然，Java 的内存回收可以由 JVM 来自动完成。如果你手动使用，则可以使用以下的方法。
 */
class Cake extends Object {
	private int id;

	public Cake(int id) {
		this.id = id;
		DealLog.log("创建对象 " + id);
	}

	/**
	 * 终结代码
	 * Java 允许定义这样的方法，它在对象被垃圾收集器析构(回收)之前调用，
	 * 这个方法叫做 finalize( )，它用来清除回收对象
	 *
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable {
		//在这里终结代码
		super.finalize();
		DealLog.log("对象回收 " + id);
	}
}