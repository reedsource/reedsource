/*
 * FileName: javaGenericClass
 * Author:   reedsource
 */
package k20泛型;

/**
 * 功能简述:
 * 〈泛型类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class javaGenericClass<T> {
	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public static void main(String[] args) {
		javaGenericClass<Integer> integerjavaGenericClass = new javaGenericClass<Integer>();
		javaGenericClass<String> stringjavaGenericClass = new javaGenericClass<String>();

		integerjavaGenericClass.add(new Integer(10));
		stringjavaGenericClass.add(new String("菜鸟教程"));

		System.out.printf("整型值为 :%d\n\n", integerjavaGenericClass.get());
		System.out.printf("字符串为 :%s\n", stringjavaGenericClass.get());

		//整型值为 :10
		//字符串为 :菜鸟教程
	}
}
