package top.ireed.deal;


import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static top.ireed.general.TopConstant.F_B;
import static top.ireed.general.TopConstant.UTF8;


/**
 * 字符串处理工具类
 *
 * @author ireed
 */
public class DealString {

	/**
	 * 删除字符串首尾空格,判断字符串是否为空
	 *
	 * @param inputString 字符串
	 * @return 删除首尾空之后的字符串
	 */
	public static String deleteEmptyString(String inputString) {
		if (inputString == null) {
			inputString = "";
		}
		//删除首尾空
		return inputString.trim();
	}


	/**
	 * 转换编码
	 *
	 * @param strString 要转换的字符串
	 * @param srcCode   原编码
	 * @param destCode  目标编码
	 * @return 转换完成的字符串
	 */
	public static String convertCode(String strString, String srcCode, String destCode) {
		String returnString = deleteEmptyString(strString);
		try {
			byte[] ascii = returnString.getBytes(srcCode);
			returnString = new String(ascii, destCode);
		} catch (Exception e) {
			DealLog.log("转换编码异常");
		}
		return returnString;
	}
//======================================================================================================


	/**
	 * 替换字符串
	 *
	 * @param originString 原字符串
	 * @param oldString    被替换字符串
	 * @param newString    替换字符串
	 * @return 替换操作后的字符串
	 */
	public static String replaceString(String originString, String oldString, String newString) {
		String returnString = originString;
		//寻找被替换的字符串位置是否不为-1
		while (returnString.contains(oldString)) {
			returnString = returnString.substring(0, returnString.indexOf(oldString)) +
					newString + returnString.substring(returnString.indexOf(oldString) +
					oldString.length(), returnString.length());
		}
		return returnString;
	}

	/**
	 * 替换Html非法字符，字符串中的字符替换为转译字符
	 *
	 * @param htmlString 要转换替换的字符串
	 * @return 替换转译后的我字符串
	 */
	public static String replaceHtmlErrorString(String htmlString) {
		//创建字符串缓冲区
		StringBuilder  returnString = new StringBuilder (htmlString.length());
		char ch;
		for (int i = 0; i < htmlString.length(); i++) {
			ch = htmlString.charAt(i);
			switch (ch) {
				case '<':
					returnString.append("&lt");
					break;
				case '>':
					returnString.append("&gt");
					break;
				case ' ':
					returnString.append("&nbsp");
					break;
				case '\\':
					returnString.append("&acute");
					break;
				default:
					returnString.append(ch);
					break;
			}
		}
		//逻辑值转换为字符串
		return returnString.toString();
	}


	/**
	 * 替换字符串中的单引号为其它字符串
	 *
	 * @param inputString 要转换单引号的字符串
	 * @return 替换完成的字符串
	 */
	public static String replaceString(String inputString) {
		StringBuilder returnString = new StringBuilder(inputString.length());
		char charOne;
		String oneCharString = "'";
		char oneChar = oneCharString.charAt(0);

		for (int i = 0; i < inputString.length(); i++) {
			charOne = inputString.charAt(i);
			if (charOne == oneChar) {
				returnString.append("''");
			} else {
				returnString.append(charOne);
			}
		}
		return returnString.toString();
	}

//======================================================================================================

	/**
	 * 数字金额格式化表达式
	 *
	 * @param num 金额数值
	 * @return 返回中文金钱格式字符串 例如￥1,234,566.00
	 */
	private static String formatMoneyString(int num) {
		//创建金钱格式化对象
		NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
		return formatc.format(num);
	}


	/**
	 * 格式化数值
	 *
	 * @param num 数值
	 * @return 格式化后的数值文本
	 */
	public static String formatString(int num) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###.0000");
		return decimalFormat.format(num);
	}

	//======================================================================================================


	/**
	 * 转换 Utf8URL编码
	 *
	 * @param urlString url转换为utf8编码格式
	 * @return 转换完成的字符串
	 */
	private static String utf8Encodeurl(String urlString) {
		StringBuilder returnString = new StringBuilder();

		for (int i = 0; i < urlString.length(); i++) {
			char c = urlString.charAt(i);
			if (c <= 255) {
				returnString.append(c);
			} else {

				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes(UTF8);
				} catch (Exception ex) {
					DealLog.log("转换到url编码错误");
				}

				for (byte aB : b) {
					int k = aB;
					if (k < 0) {
						k += 256;
					}
					returnString.append(F_B).append(Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return returnString.toString();
	}

	/**
	 * URL解码为Utf8字符串
	 * 将统一资源定位符 URL 解码为可识别的utf8字符串
	 *
	 * @param urlString Utf8URL编码文本
	 * @return 转换完成的字符串
	 */
	public static String urlEncodeUtf8(String urlString) {
		StringBuilder result = new StringBuilder();
		int p;

		if (urlString != null && urlString.length() > 0) {
			urlString = urlString.toLowerCase();
			p = urlString.indexOf("%e");
			if (p == -1) {
				return urlString;
			}
			while (p != -1) {
				result.append(urlString.substring(0, p));
				urlString = urlString.substring(p, urlString.length());
				if ("".equals(urlString) || urlString.length() < 9) {
					return result.toString();
				}
				result.append(utf8ConversionString(urlString.substring(0, 9)));
				urlString = urlString.substring(9, urlString.length());
				p = urlString.indexOf("%e");
			}
		}
		return result + urlString;
	}

	/**
	 * utf8URL编码转字符
	 *
	 * @param utf8String 需要转换的utf8字符串
	 * @return 转换完成的字符串
	 */
	private static String utf8ConversionString(String utf8String) {
		String result;

		if (utf8codeCheck(utf8String)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(utf8String.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(utf8String.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(utf8String.substring(7, 9), 16) - 256);
			try {
				result = new String(code, UTF8);
			} catch (UnsupportedEncodingException ex) {
				result = null;
			}
		} else {
			result = utf8String;
		}
		return result;
	}


	/**
	 * utf8代码检查
	 *
	 * @param text 字符串
	 * @return 检查完成的utf8字符串
	 */
	private static boolean utf8codeCheck(String text) {
		StringBuilder sign = new StringBuilder();
		//字符串文本开头
		String startString = "%e";

		//判断首文本是否符合
		if (text.startsWith(startString)) {
			for (int p = 0; p != -1; ) {
				p = text.indexOf(F_B, p);
				if (p != -1) {
					p++;
				}
				//等同于+=
				sign.append(p);
			}
		}
		return "147-1".equals(sign.toString());
	}

	/**
	 * Utf8Url编码判断
	 *
	 * @param bytes 字符串
	 * @return 是否为utf8字符串
	 */
	private static boolean utf8UrlCheck(byte[] bytes) {

		if (bytes != null && bytes.length > 0) {
			boolean foundStartByte = false;
			int requireByte = 0;
			for (int i = 0; i < bytes.length; i++) {
				byte current = bytes[i];
				//当前字节小于128，标准ASCII码范围
				if ((current & 0x80) == 0x00) {
					if (foundStartByte) {
						foundStartByte = false;
						requireByte = 0;
					}
					//当前以0x110开头，标记2字节编码开始，后面需紧跟1个0x10开头字节
				} else if ((current & 0xC0) == 0xC0) {
					foundStartByte = true;
					requireByte = 1;
					//当前以0x1110开头，标记3字节编码开始，后面需紧跟2个0x10开头字节
				} else if ((current & 0xE0) == 0xE0) {
					foundStartByte = true;
					requireByte = 2;
					//当前以0x11110开头，标记4字节编码开始，后面需紧跟3个0x10开头字节
				} else if ((current & 0xF0) == 0xF0) {
					foundStartByte = true;
					requireByte = 3;
					//当前以0x111110开头，标记5字节编码开始，后面需紧跟4个0x10开头字节
				} else if ((current & 0xE8) == 0xE8) {
					foundStartByte = true;
					requireByte = 4;
					//当前以0x1111110开头，标记6字节编码开始，后面需紧跟5个0x10开头字节
				} else if ((current & 0xEC) == 0xEC) {
					foundStartByte = true;
					requireByte = 5;
					//当前以0x10开头，判断是否满足utf8编码规则
				} else if ((current & 0x80) == 0x80) {
					if (foundStartByte) {
						requireByte--;
						//出现多个0x10开头字节，个数满足，发现utf8编码字符，直接返回
						if (requireByte == 0) {
							return true;
						}
						//虽然经当前以0x10开头，但前一字节不是以0x110|0x1110|0x11110肯定不是utf8编码，直接返回
					} else {
						return false;
					}
					//发现0x8000~0xC000之间字节，肯定不是utf8编码
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 倒找字符出现的位置
	 *
	 * @param str
	 * @param ch
	 * @param lin
	 * @return
	 */
	public static int inverted(String str, char ch, int lin) {
		char[] array = str.toCharArray();
		for (int i = array.length - 1; i > -1; i--) {
			if (array[i] == ch && --lin == 0) {
				return i;
			}
		}
		return -1;
	}

	private static final char[] SS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	/**
	 * 数值字符串解压
	 *
	 * @param str
	 * @return
	 */
	public static String jy(String str) {
		try {
			int a = 0;
			for (int i = 0; i < str.length(); i++) {
				for (int j = 0; j < SS.length; j++) {
					if (str.charAt(i) == SS[j]) {
						a = a * 62 + j;
						break;
					}
				}
			}
			return String.valueOf(a);
		} catch (Exception e) {
			DealLog.log("解压文本异常,无法解压缩");
			return str;
		}
	}

	/**
	 * 数值字符串压缩   注意有长度限制
	 *
	 * @param str
	 * @return
	 */
	public static String ys(String str) {
		try {
			int a = Integer.parseInt(str);
			int yu;
			int z;
			StringBuilder s = new StringBuilder();
			do {
				z = a / 62;
				yu = a % 62;
				//如果余数为0,刚好整除
				if (yu == 0) {
					s.insert(0, "0");
				} else {
					s.insert(0, SS[yu]);
				}
				a = z;
				if (0 < a && a < 62) {
					s.insert(0, SS[a]);
				}
			} while (a >= 62);
			return String.valueOf(s);
		} catch (NumberFormatException e) {
			DealLog.log("数值文本异常,数值文本无法压缩");
			return str;
		}
	}


	public static void main(String[] args) throws UnsupportedEncodingException {
		DealLog.log(DealString.formatMoneyString(1234566));

		String url;
		url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr=";
		DealLog.log(url);
		//判断代码是否为utf8编码,并解析
		if (DealString.utf8UrlCheck(url.getBytes())) {
			String m = DealString.urlEncodeUtf8(url);
			DealLog.log(m);
			//将utf8字符集转换为url
			DealLog.log(DealString.utf8Encodeurl(m));
		}

		String a = "1321312";
		DealLog.log("判断字符串的首位是否为特定字符串" + a.startsWith("1"));
		DealLog.log("判断字符串的特定位置是否为特定字符串(0开始)" + a.startsWith("1", 3));


		String str = "<a href=\"http://www.baidu.com\">百度一下</a>";
		DealLog.log(DealString.utf8UrlCheck(str.getBytes(UTF8)));
		DealLog.log(DealString.utf8UrlCheck(str.getBytes("gbk")));

		DealLog.log("开始数值字符压缩测试------------------------------------------------");
		//压缩测试

		DealLog.log("1 " + DealString.ys("1"));
		DealLog.log("62 " + DealString.ys("62"));
		DealLog.log("63 " + DealString.ys("63"));
		DealLog.log("3844 " + DealString.ys("3844"));
		DealLog.log("3845 " + DealString.ys("3845"));
		DealLog.log("238328 " + DealString.ys("238328"));
		DealLog.log("238329 " + DealString.ys("238329"));
		DealLog.log("14776336 " + DealString.ys("14776336"));
		DealLog.log("14776337 " + DealString.ys("14776337"));
		DealLog.log("916132832 " + DealString.ys("916132832"));
		DealLog.log("465464644 " + DealString.ys("465464644"));
		DealLog.log("46545 " + DealString.ys("46545"));
		DealLog.log("845545 " + DealString.ys("845545"));
		DealLog.log("845645 " + DealString.ys("845645"));
		DealLog.log("45648744 " + DealString.ys("45648744"));
		DealLog.log("开始解压");
		DealLog.log(DealString.jy(DealString.ys("45648744")));
	}
}

