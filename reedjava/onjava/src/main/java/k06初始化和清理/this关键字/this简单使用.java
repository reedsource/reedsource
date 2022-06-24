/*
 * Author:   reedsource
 */
package k06初始化和清理.this关键字;

import org.junit.Test;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/23 9:41
 * reedsource@189.cn
 */
public class this简单使用 {
	int i = 0;

	this简单使用 increment() {
		i++;
		//这里的this返回了当前对象的引用
		return this;
	}

	void print() {
		System.out.println("i = " + i);
	}

	@Test
	public void this简单使用_Test() {
		this简单使用 x = new this简单使用();
		x.increment().increment().increment().print();
	}

	//i = 3
}
