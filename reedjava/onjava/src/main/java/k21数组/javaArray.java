/*
 * FileName: javaArray
 * Author:   reedsource
 */
package k21数组;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;
import top.ireed.general.TopConstant;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能简述:
 * 〈java数组〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaArray {
	public static void main(String[] args) {
		c0();
		c1();
		c2();
	}
	/**
	 * 创建数组
	 */
	@Test
	public void javaArrayTest() {
		//动态初始化
		int[] arr = new int[5];
		//数组元素的默认初始化
		Assert.assertEquals(0, arr[1]);
		//静态初始化
		int[] arr1 = {11, 12, 13, 14, 15};
		for (int i = 0; i < TopConstant.INT5; i++) {
			arr[i] = i;
			DealLog.log(arr[i]);
			DealLog.log(arr1[i]);
		}
		Assert.assertEquals(5, arr.length);

		/**声明二维数组：有两行，列数待定，数组结构 = { { }, { } }
		 静态初始化可用于不规则二维数组的初始化*/
		String[][] strings = {{"E1", "E2"}, {"E1", "E2", "E3"}};
		Assert.assertEquals(2, strings.length);
		Assert.assertEquals(3, strings[1].length);
		Assert.assertEquals("E3", strings[1][2]);
	}

	@Test
	public void javaArrayTest1() {
		//打印数组
		int[] intArray = {1, 2, 3, 4, 5};
		String intArrayString = Arrays.toString(intArray);
		Assert.assertEquals("[I@ed9d034", Arrays.toString(intArray));
		Assert.assertEquals("[1, 2, 3, 4, 5]", intArrayString);

		//从数组创建一个ArrayList
		String[] stringArray = {"a", "b", "c", "d", "e"};
		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(stringArray));
		Assert.assertEquals("[a, b, c, d, e]", arrayList.toString());

		//检查数组是否包含某一个值
		Assert.assertTrue(Arrays.asList(stringArray).contains("a"));
	}

	/**
	 * 普通一维数组创建及遍历
	 */
	private static void c0() {
		//创建了一个长度为10的数组,把新创建的数组的引用赋值给变量 myList
		double[] myList = new double[10];

		//也可以写为
		String[] a = {"1", "2", "3", "4"};

		//遍历 For-Each 循环
		for (String m : a) {
			DealLog.log(m);
		}

		//数组的元素是通过索引访问的。数组索引从 0 开始，所以索引值从 0 到 a.length-1。
		for (int i = 0; i <= a.length - 1; i++) {
			DealLog.log(a[i]);
		}
	}

	/**
	 * 二维数组
	 */
	private static void c1() {
		//普通
		String[][] str = new String[3][4];

		//声明二维数组：有两行，列数待定，数组结构 = { { }, { } }
		String[][] m = {{"E1", "E2"}, {"E1", "E2", "E3"}};
		DealLog.log(m[1][2]);

		//E3

		//可以理解为行数和列数
		String s[][] = new String[2][];
		s[0] = new String[2];
		s[1] = new String[3];
		s[0][0] = new String("Good");
		s[0][1] = new String("Luck");
		s[1][0] = new String("to");
		s[1][1] = new String("you");
		s[1][2] = new String("!");

		//s[0]=new String[2] 和 s[1]=new String[3]
		// 是为最高维分配引用空间，也就是为最高维限制其能保存数据的最长的长度，
		// 然后再为其每个数组元素单独分配空间 s0=new String("Good") 等操作


		//多维数组的引用
		DealLog.log(s[1][1]);

		//you

	}


	/**
	 * Arrays操作数组
	 * 静态
	 * <p>
	 * 给数组赋值：通过 fill 方法。
	 * 对数组排序：通过 sort 方法,按升序。
	 * 比较数组：通过 equals 方法比较数组中元素值是否相等。
	 * 查找数组元素：通过 binarySearch 方法能对排序好的数组进行二分查找法操作。
	 */
	private static void c2() {

		int[] array = new int[5];
		// 填充数组
		Arrays.fill(array, 5);
		DealLog.log("填充数组：Arrays.fill(array, 5)：");
		output(array);
		// 将数组的第2和第3个元素赋值为8
		Arrays.fill(array, 2, 4, 8);
		DealLog.log("将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：");
		output(array);
		int[] array1 = {7, 8, 3, 2, 12, 6, 3, 5, 4};
		// 对数组的第2个到第6个进行排序进行排序
		Arrays.sort(array1, 2, 7);
		DealLog.log("对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：");
		output(array1);
		// 对整个数组进行排序
		Arrays.sort(array1);
		DealLog.log("对整个数组进行排序：Arrays.sort(array1)：");
		output(array1);
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
	}

	/**
	 * 倒序排序
	 *
	 * @param arr
	 * @return
	 */
	private static int[] c3(int[] arr) {
		int[] result = new int[arr.length];
		for (int i = 0, j = result.length - 1; i < arr.length; i++, j--) {
			result[j] = arr[i];
		}
		return result;
	}


	/**
	 * 打印
	 *
	 * @param array
	 * @return
	 */
	private static void output(int[] array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				DealLog.log(array[i] + " ");
			}
		}
		DealLog.log();

	}
}
