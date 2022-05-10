/*
 * FileName: javaConditionCase
 * Author:   reedsource
 */
package k05控制流.if条件;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java条件语句案例〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaConditionCase {
	public static void main(String[] args) {
		c0(9);
		c1(9);
		c2(9);
		c3(9);
	}

	/**
	 * 输入由*号组成的菱形
	 */
	private static void c0(float c) {
		float p = c / 2;// 升序排序
		float d;// 声明行数变量
		float e;// 声明打印*号的变量
		float f;// 声明打印空格的变量
		float s = c % 2;// 取模
		if (s == 0) {
			DealLog.log("你输入的数据不能形成菱形结构");
		} else {
			for (d = 1; d <= p; d++) {
				for (f = p; f >= d; f--) {
					DealLog.log(" ");
				}
				for (e = 1; e <= d * 2 - 1; e++) {
					if (e == 1 || e == d * 2 - 1) {
						// 如果是第一个数和最后一个数，就输入*
						DealLog.log("*");
					} else {
						// 否则输入空格
						DealLog.log(" ");
					}
				}
				DealLog.log("");
			}
		}
	}

	// 下面是打印倒序的代码
	private static void c1(float m) {
		float i;// 声明行数变量
		float j;// 声明打印*号的变量
		float k;// 声明打印空格数的变量
		float n = m / 2 + 1;// 倒序排序
		float o = m % 2;// m取模
		if (o == 0) {
			DealLog.log("");
		} else {
			for (i = 1; i <= n; i++)// 行数循环；
			{
				// 打印*号前打印空格；
				for (k = 0; k < i - 1; k++) {
					DealLog.log(" ");
				}
				// 下面打印*号个数的循环；
				for (j = (n - k) * 2 - 2; j >= 1; j--)// 打印*号个数的循环；
				{
					if (j == (n - k) * 2 - 2 || j == 1) {
						DealLog.log("*");
					} else {
						DealLog.log(" ");
					}
				}
				// 打印完*号换行打印；
				DealLog.log();
			}
		}
	}

	/**
	 * 输入由*号组成的菱形优化
	 *
	 * @param h h是方法中的参数，也是行数
	 */
	private static void c2(int h) {
		int a, b;    //a是要生成的菱形行数
		int i, j;    //i j是循环结构参数

		for (i = 1; i <= h; i++) {         //逐行打印
			for (j = 1; j <= h; j++) {       //每行打印个数与行数保持一致
				//下面语句是菱形四条边的函数，在边上的坐标点，打印*，否则打印空格
				if (j == (h + 3) / 2 - i || j == (h - 1) / 2 + i || j == i - (h - 1) / 2 || j == (3 * h + 1) / 2 - i) {
					DealLog.log("*");
				} else {
					DealLog.log(" ");
				}
			}
			DealLog.log();        //第 i 行打印完换行
		}
	}

	/**
	 * 打印一个含外部边框的菱形
	 *
	 * @param h 行数
	 */
	private static void c3(int h) {
		for (int m = 0; m <= h + 1; m++) {
			for (int n = 0; n <= h + 1; n++) {
				if ((m == 0 && n >= 0) || (n == 0 && m > 0) || (m == h + 1 && h + 1 > n && n > 0)
						|| (n == h + 1 && m > 0)) {
					DealLog.log(" ");
					DealLog.log("*");
				} else if (n == (h + 3) / 2 - m || n == (h - 1) / 2 + m || n == m - (h - 1) / 2 || n == (3 * h + 1) / 2 - m) {
					DealLog.log(" ");
					DealLog.log("@");
				} else {
					DealLog.log(" ");
					DealLog.log(" ");
				}
			}
			DealLog.log();
		}
		/*
		 * for (int i=1;i<=h;i++){ //逐行打印 for (int j=1;j<=h;j++){
		 * //每行打印个数数行数保持一致 //下面是菱形四天便的函数，在边上的坐标点打印*，否则打印空格 if
		 * (j==(h+3)/2-i||j==(h-1)/2+i||j==i-(h-1)/2||j==(3*h+1)/2-i){
		 * DealLog.log("*"); }else { DealLog.log(" "); } }
		 * DealLog.log(); }
		 */
	}


}
