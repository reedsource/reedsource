/*
 * FileName: javaAnomaly
 * Author:   reedbook
 */
package k15异常;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java异常〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class javaAnomaly {

	/*
	检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。
	例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。
	运行时异常： 运行时异常是可能被程序员避免的异常。
	与检查性异常相反，运行时异常可以在编译时被忽略。
	错误： 错误不是异常，而是脱离程序员控制的问题。
	错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。

	Java 语言定义了一些异常类在 java.lang 标准包中。

	标准运行时异常类的子类是最常见的异常类。由于 java.lang 包是默认加载到所有的 Java 程序的，所以大部分从运行时异常类继承而来的异常都可以直接使用。
	*/
	public static void main(String[] args) {
		try {
			// 程序代码
			int a[] = new int[2];
			DealLog.log("输出:" + a[3]);
			//Catch 块
		} catch (ArrayIndexOutOfBoundsException e) {
			DealLog.log("异常  :" + e);
		}

		//异常  :java.lang.ArrayIndexOutOfBoundsException: 3



		/*
		try{
		   // 程序代码
		}catch(异常类型1 异常的变量名1){
		  // 程序代码
		}catch(异常类型2 异常的变量名2){
		  // 程序代码
		}catch(异常类型2 异常的变量名2){
		  // 程序代码
		}

		上面的代码段包含了 3 个 catch块。
		可以在 try 语句后面添加任意数量的 catch 块。
		如果保护代码中发生异常，异常被抛给第一个 catch 块。
		如果抛出异常的数据类型与 ExceptionType1 匹配，它在这里就会被捕获。
		如果不匹配，它会被传递给第二个 catch 块。
		如此，直到异常被捕获或者通过所有的 catch 块。
		*/

	}

/*
Java 的非检查性异常

ArithmeticException	当出现异常的运算条件时，抛出此异常。例如，一个整数"除以零"时，抛出此类的一个实例。
ArrayIndexOutOfBoundsException	用非法索引访问数组时抛出的异常。如果索引为负或大于等于数组大小，则该索引为非法索引。
ArrayStoreException	试图将错误类型的对象存储到一个对象数组时抛出的异常。
ClassCastException	当试图将对象强制转换为不是实例的子类时，抛出该异常。
IllegalArgumentException	抛出的异常表明向方法传递了一个不合法或不正确的参数。
IllegalMonitorStateException	抛出的异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器而本身没有指定监视器的线程。
IllegalStateException	在非法或不适当的时间调用方法时产生的信号。换句话说，即 Java 环境或 Java 应用程序没有处于请求操作所要求的适当状态下。
IllegalThreadStateException	线程没有处于请求操作所要求的适当状态时抛出的异常。
IndexOutOfBoundsException	指示某排序索引（例如对数组、字符串或向量的排序）超出范围时抛出。
NegativeArraySizeException	如果应用程序试图创建大小为负的数组，则抛出该异常。
NullPointerException	当应用程序试图在需要对象的地方使用 null 时，抛出该异常
NumberFormatException	当应用程序试图将字符串转换成一种数值类型，但该字符串不能转换为适当格式时，抛出该异常。
SecurityException	由安全管理器抛出的异常，指示存在安全侵犯。
StringIndexOutOfBoundsException	此异常由 String 方法抛出，指示索引或者为负，或者超出字符串的大小。
UnsupportedOperationException	当不支持请求的操作时，抛出该异常。

检查性异常类

ClassNotFoundException	应用程序试图加载类时，找不到相应的类，抛出该异常。
CloneNotSupportedException	当调用 Object 类中的 clone 方法克隆对象，但该对象的类无法实现 Cloneable 接口时，抛出该异常。
IllegalAccessException	拒绝访问一个类的时候，抛出该异常。
InstantiationException	当试图使用 Class 类中的 newInstance 方法创建一个类的实例，而指定的类对象因为是一个接口或是一个抽象类而无法实例化时，抛出该异常。
InterruptedException	一个线程被另一个线程中断，抛出该异常。
NoSuchFieldException	请求的变量不存在
NoSuchMethodException	请求的方法不存在

异常方法Throwable 类的主要方法

public String getMessage()          返回关于发生的异常的详细信息。这个消息在Throwable 类的构造函数中初始化了。
public Throwable getCause()         返回一个Throwable 对象代表异常原因。
public String toString()            使用getMessage()的结果返回类的串级名字。
public void printStackTrace()       打印toString()结果和栈层次到System.err，即错误输出流。
public StackTraceElement [] getStackTrace()
									返回一个包含堆栈层次的数组。下标为0的元素代表栈顶，最后一个元素代表方法调用堆栈的栈底。
public Throwable fillInStackTrace()
									用当前的调用栈层次填充Throwable 对象栈层次，添加到栈层次任何先前信息中。

throws/throw 关键字：
如果一个方法没有捕获到一个检查性异常，那么该方法必须使用 throws 关键字来声明。
throws 关键字放在方法签名的尾部。
也可以使用 throw 关键字抛出一个异常，无论它是新实例化的还是刚捕获到的。

public class className
{
  public void deposit(double amount) throws RemoteException
  {
    // Method implementation
    throw new RemoteException();
  }
  //Remainder of class definition
}

一个方法可以声明抛出多个异常，多个异常之间用逗号隔开。

例如，下面的方法声明抛出 RemoteException 和 InsufficientFundsException：

import java.io.*;
public class className
{
   public void withdraw(double amount) throws RemoteException,
                              InsufficientFundsException
   {
       // Method implementation
   }
   //Remainder of class definition
}

finally关键字
finally 关键字用来创建在 try 代码块后面执行的代码块。

无论是否发生异常，finally 代码块中的代码总会被执行。

在 finally 代码块中，可以运行清理类型等收尾善后性质的语句。

finally 代码块出现在 catch 代码块最后，语法如下：

try{
  // 程序代码
}catch(异常类型1 异常的变量名1){
  // 程序代码
}catch(异常类型2 异常的变量名2){
  // 程序代码
}finally{
  // 程序代码
}

注意下面事项：

catch 不能独立于 try 存在。
在 try/catch 后面添加 finally 块并非强制性要求的。
try 代码后不能既没 catch 块也没 finally 块。
try, catch, finally 块之间不能添加任何代码。


声明自定义异常
在 Java 中你可以自定义异常。编写自己的异常类时需要记住下面的几点。
所有异常都必须是 Throwable 的子类。
如果希望写一个检查性异常类，则需要继承 Exception 类。
如果你想写一个运行时异常类，那么需要继承 RuntimeException 类。
可以像下面这样定义自己的异常类：

class MyException extends Exception{
}
只继承Exception 类来创建的异常类是检查性异常类。

下面的 InsufficientFundsException 类是用户定义的异常类，它继承自 Exception。

一个异常类和其它任何类一样，包含有变量和方法。


在Java中定义了两种类型的异常和错误。

JVM(Java虚拟机) 异常：由 JVM 抛出的异常或错误。
例如：NullPointerException 类，ArrayIndexOutOfBoundsException 类，ClassCastException 类。
程序级异常：由程序或者API程序抛出的异常。
例如 IllegalArgumentException 类，IllegalStateException 类。
*/
}
