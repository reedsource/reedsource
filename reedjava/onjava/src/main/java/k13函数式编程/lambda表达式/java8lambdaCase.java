/*
 * FileName: java8lambdaCase
 * Author:   reedsource
 */
package k13函数式编程.lambda表达式;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java8lambda案例特性〉
 * <p>
 * 注意以下两点：
 * <p>
 * Lambda 表达式主要用来定义行内执行的方法类型接口，
 * 例如，一个简单方法接口。在下面例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。
 * 然后我们定义了sayMessage的执行。
 * Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class java8lambdaCase {
	public static void main(String args[]) {

		//特征1  可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。

		// 接收2个int型整数,返回他们的和
		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 接受2个参数(数字),并返回他们的差值
		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		//特征2 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号
		//特征3 可选的大括号：如果主体包含了一个语句，就不需要使用大括号

		// 接收一个参数(数字类型),返回乘积
		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> a * b;

		// 接收一个参数(数字类型),返回其2倍的除
		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		DealLog.log("10 + 5 = " + operate(10, 5, addition));
		DealLog.log("10 - 5 = " + operate(10, 5, subtraction));
		DealLog.log("10 x 5 = " + operate(10, 5, multiplication));
		DealLog.log("10 / 5 = " + operate(10, 5, division));

		//特征4 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值

		// 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
		// 不用括号
		GreetingService greetService1 = message -> DealLog.log("你好 " + message);
		// 用括号
		GreetingService greetService2 = (message) -> DealLog.log("你好 " + message);

		greetService1.sayMessage("世界");
		greetService2.sayMessage("lambda");


	}


	interface GreetingService {
		void sayMessage(String message);
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	private static int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}
