/*
 * FileName: javaVisit
 * Author:   reedsource
 */
package k07访问控制;

/**
 * 功能简述:
 * 〈java访问范围修饰〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaVisit {
	/*
	default (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。
	private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
	public : 对所有类可见。使用对象：类、接口、变量、方法
	protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。
	修饰符	当前类	同一包内	子孙类(同一包)	子孙类(不同包)	其他包
	public	    Y	    Y	    Y	        Y	            Y
	protected	Y	    Y	    Y	        Y/N（说明）     	N
	default	    Y	    Y	    Y	        N	            N
	private	    Y	    N	    N	        N	            N

	接口里的变量都隐式声明为 public static final,而接口里的方法默认情况下访问权限为 public
	*/
	String version = "1.5.1";

	boolean processOrder() {
		return true;
	}
}
