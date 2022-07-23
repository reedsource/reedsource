/*
 * FileName: 创建_数组
 * Author:   reedsource
 */
package k21数组;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈数组创建〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/16 23:07
 * reedsource@189.cn
 */
public class 普通数组 {
	/**
	 * 创建数组
	 */
	@Test
	public void 普通数组_Test() {
		//动态初始化
		int[] intArray = new int[5];
		double[] doubleArray = new double[10];
		String[] StringArray = new String[10];
		intArray[0] = 1;
		doubleArray[0] = 1.1;
		StringArray[0] = "1";

		//静态初始化
		int[] arr1 = {11, 12, 13, 14, 15};
		String[] a = {"1", "2", "3", "4"};
		int[] intArray2 = new int[]{1, 2, 3, 4, 5};
		DealLog.log(intArray2);

		//数组的初始化默认值
		//数组元素的默认初始化
		Assert.assertEquals(0, intArray[1]);
		DealLog.log(doubleArray[1]);
		Assert.assertNull(StringArray[1]);

		//组数遍历
		//数组的元素是通过索引访问的。数组索引从 0 开始，所以索引值从 0 到 a.length-1。
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = i;
			DealLog.log(arr1[i]);
		}

		//遍历 For-Each 循环
		for (String m : a) {
			DealLog.log(m);
		}

	}
}
