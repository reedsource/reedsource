/*
 * FileName: guessCase
 * Author:   reedbook
 */
package k21数组;

import top.ireed.deal.DealLog;

import java.util.Scanner;

/**
 * 功能简述:
 * 〈猜数字游戏〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class guessCase {
	private static Scanner scan;

	// 主方法
	public static void main(String[] args) {

		int[] arr = new int[5];
		int[] arr1 = {1, 2, 3, 4, 5};
		for (int i = 0; i < 5; i++) {
			arr[i] = i;
			DealLog.log(arr[i]);
			DealLog.log(arr1[i]);
		}


		scan = new Scanner(System.in);
		char[] chs = generate();
		DealLog.log(chs);
		int count = 0; // 猜错的次数
		while (true) { // 自造死循环
			DealLog.log("猜吧!");
			String str = scan.next().toUpperCase(); // 获取用户输入的字符串
			if ("EXIT".equals(str)) { // 判断字符串内容相等
				DealLog.log("下次再来吧!");
				break;
			}
			char[] input = str.toCharArray(); // 将字符串转换为字符数组
			int[] result = check(chs, input);
			if (result[0] == chs.length) { // 对
				int score = 100 * chs.length - 10 * count;
				DealLog.log("恭喜你，猜对了!得分为:" + score);
				break;
			} else {
				count++;
				DealLog.log("字符对个数为:" + result[1] + "，位置对个数为:" + result[0]);
			}
		}
	}

	// 生成随机字符数组chs
	public static char[] generate() {
		char[] chs = new char[5];
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z'};
		boolean[] flags = new boolean[letters.length];
		for (int i = 0; i < chs.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * letters.length);
			} while (flags[index] == true);
			chs[i] = letters[index];
			flags[index] = true;
		}
		// i=0 index=0 chs[0]='A' flags[0]=true
		// i=1 index=25 chs[1]='Z' flags[25]=true
		// i=2 index=0/25/0/25/1 chs[2]='B' flags[1]=true
		return chs;
	}

	// 对比:随机字符数组chs与用户输入的字符数组input
	public static int[] check(char[] chs, char[] input) {
		int[] result = new int[2]; // (0,0)
		for (int i = 0; i < chs.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if (chs[i] == input[j]) {
					result[1]++;
					if (i == j) {
						result[0]++;
					}
					break;
				}
			}
		}
		return result;
	}
}
