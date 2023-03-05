/*
 * Author:   reedsource
 */
package k18字符串;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/6 13:56
 * reedsource@189.cn
 */
public class String自有方法解析 {
	public static void main(String[] args) {
		String a = "/druid/*";
		String b = a.replaceAll("\\*", "js/common.js");
		DealLog.log(b);
		String c = a.replaceAll("/*", "/js/common.js");
		DealLog.log(c);
		String d = a.replace("\\*", "js/common.js");
		DealLog.log(d);
		String e = a.replace("/*", "/js/common.js");
		DealLog.log(e);
	}
}
