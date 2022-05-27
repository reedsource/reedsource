/*
 * FileName: Arrays数组操作集
 * Author:   reedsource
 */
package k21数组;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:
 * 〈Arrays数组工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/16 23:50
 * reedsource@189.cn
 */
public class Arrays数组操作集 {

	/**
	 * Arrays操作数组
	 * 静态
	 * <p>
	 * 给数组赋值：通过 fill 方法。
	 * 对数组排序：通过 sort 方法,按升序。
	 * 比较数组：通过 equals 方法比较数组中元素值是否相等。
	 * 查找数组元素：通过 binarySearch 方法能对排序好的数组进行二分查找法操作。
	 */
	@Test
	public void Arrays数组操作集_test() {
		/*
		1	public static int binarySearch(Object[] a, Object key)
		用二分查找算法在给定数组中搜索给定值的对象(Byte,Int,double等)。
		数组在调用前必须排序好的。如果查找值包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。

		2	public static boolean equals(long[] a, long[] a2)
		如果两个指定的 long 型数组彼此相等，则返回 true。
		如果两个数组包含相同数量的元素，并且两个数组中的所有相应元素对都是相等的，则认为这两个数组是相等的。换句话说，如果两个数组以相同顺序包含相同的元素，则两个数组是相等的。同样的方法适用于所有的其他基本数据类型（Byte，short，Int等）。

		3	public static void fill(int[] a, int val)
		将指定的 int 值分配给指定 int 型数组指定范围中的每个元素。
		同样的方法适用于所有的其他基本数据类型（Byte，short，Int等）。

		4	public static void sort(Object[] a)
		对指定对象数组根据其元素的自然顺序进行升序排列。
		同样的方法适用于所有的其他基本数据类型（Byte，short，Int等）。
		*/

		//int数组转字符串
		int[] intArray = {1, 2, 3, 4, 5};
		Assert.assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(intArray));

		//字符串数组转ArrayList
		String[] stringArray = {"a", "b", "c", "d", "e"};
		List<String> arrayList = new ArrayList<>(Arrays.asList(stringArray));
		Assert.assertEquals("[a, b, c, d, e]", arrayList.toString());

		//检查数组是否包含某一个值
		Assert.assertTrue(Arrays.asList(stringArray).contains("a"));

		int[] array = new int[5];
		// 填充数组
		Arrays.fill(array, 5);
		DealLog.log("填充数组：Arrays.fill(array, 5)：", Arrays.toString(array));

		// 将数组的第2和第3个元素赋值为8
		Arrays.fill(array, 2, 4, 8);
		DealLog.log("将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：", Arrays.toString(array));

		int[] array1 = {7, 8, 3, 2, 12, 6, 3, 5, 4};
		// 对数组的第2个到第6个进行排序进行排序
		Arrays.sort(array1, 2, 7);
		DealLog.log("对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：", Arrays.toString(array));

		// 对整个数组进行排序
		Arrays.sort(array1);
		DealLog.log("对整个数组进行排序：Arrays.sort(array1)：", Arrays.toString(array));

		// 比较数组元素是否相等
		DealLog.log("比较数组元素是否相等:Arrays.equals(array, array1):" + "\n" + Arrays.equals(array, array1));

		int[] array2 = array1.clone();
		DealLog.log("克隆后数组元素是否相等:Arrays.equals(array1, array2):" + "\n" + Arrays.equals(array1, array2));

		// 使用二分搜索算法查找指定元素所在的下标（必须是排序好的，否则结果不正确）
		Arrays.sort(array1);
		DealLog.log("元素3在array1中的位置：Arrays.binarySearch(array1, 3)：" + "\n" + Arrays.binarySearch(array1, 3));

		// 如果不存在就返回负数
		DealLog.log("元素9在array1中的位置：Arrays.binarySearch(array1, 9)：" + "\n" + Arrays.binarySearch(array1, 9));

		//填充数组：Arrays.fill(array, 5)：
		//5 5 5 5 5
		//将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：
		//5 5 8 8 5
		//对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：
		//7 8 2 3 3 6 12 5 4
		//对整个数组进行排序：Arrays.sort(array1)：
		//2 3 3 4 5 6 7 8 12
		//比较数组元素是否相等:Arrays.equals(array, array1):
		//false
		//克隆后数组元素是否相等:Arrays.equals(array1, array2):
		//true
		//元素3在array1中的位置：Arrays.binarySearch(array1, 3)：
		//1
		//元素9在array1中的位置：Arrays.binarySearch(array1, 9)：
		//-9
	}
}
