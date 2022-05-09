/*
 * FileName: JavaBufferedReader
 * Author:   reedbook
 */
package k14流.控制台输入;

import top.ireed.deal.DealLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 功能简述:
 * 〈读取控制台输入〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class JavaBufferedReader {
	public static void main(String[] args) {
		try {
			c0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从控制台读取多字符输入
	 * <p>
	 * 从 BufferedReader 对象读取一个字符要使用 read() 方法
	 * <p>
	 * 从标准输入读取一个字符串需要使用 BufferedReader 的 readLine() 方法
	 *
	 * @throws IOException 异常
	 */
	private static void c0() throws IOException {
		char c;
		// 使用 System.in 创建 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DealLog.log("输入字符, 按下 'q' 键退出。");
		// 逐个读取字符
		do {
			c = (char) br.read();
			DealLog.log(c);
		} while (c != 'q');

		DealLog.log("输入字符串,直到end结束");
		//字符串整体读取字符串
		String str;
		do {
			str = br.readLine();
			DealLog.log(str);
		} while (!"end".equals(str));

		//write() 把字符 "A" 和紧跟着的换行符输出到屏幕
		int b = 'A';
		System.out.write(b);
		System.out.write('\n');
	}
}
