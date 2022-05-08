/*
 * FileName: javaDate
 * Author:   reedbook
 */
package k03对象.包装类.日期时间.Date;

import top.ireed.deal.DealDate;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 功能简述:
 * 〈java时间类学习〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaDate {

	public static void main(String[] args) throws TopException {
		c0();
		c1();
		c2();
		c3();
		c4();
		c5();
		c6();
	}

	/**
	 * 获取当前日期时间
	 * <p>
	 * Date对象创建以后，可以调用下面的方法。
	 * 1	boolean after(Date date)        若当调用此方法的Date对象在指定日期之后返回true,否则返回false。
	 * 2	boolean before(Date date)       若当调用此方法的Date对象在指定日期之前返回true,否则返回false。
	 * 3	Object clone( )                 返回此对象的副本。
	 * 4	int compareTo(Date date)        比较当调用此方法的Date对象和指定日期。两者相等时候返回0。调用对象在指定日期之前则返回负数。调用对象在指定日期之后则返回正数。
	 * 5	int compareTo(Object obj)       若obj是Date类型则操作等同于compareTo(Date) 。否则它抛出ClassCastException。
	 * 6	boolean equals(Object date)     当调用此方法的Date对象和指定日期相等时候返回true,否则返回false。
	 * 7	long getTime( )                 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
	 * 8	int hashCode( )                 返回此对象的哈希码值。
	 * 9	void setTime(long time)         用自1970年1月1日00:00:00 GMT以后time毫秒数设置时间和日期。
	 * 10	String toString( )              把此 Date 对象转换为以下形式的 String： dow mon dd hh:mm:ss zzz yyyy 其中： dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)。
	 */
	private static void c0() {
		// 初始化 Date 对象
		Date date = new Date();
		// 使用 toString() 函数显示日期时间
		DealLog.log(date.toString());

		//Sat Nov 02 18:48:05 CST 20
	}

	/**
	 * 日期比较
	 * 比较两个时间的大小
	 */
	private static void c1() throws TopException {
		Date date = DealDate.getDate("2019-2-20");
		Date date1 = DealDate.getDate("2019-2-18");
		//方法1 使用 getTime() 方法获取两个日期（自1970年1月1日经历的毫秒数值），然后比较这两个值
		DealLog.log(date.getTime() - date1.getTime());

		//方法2 使用方法 before()，after() 和 equals()  大于返回false
		DealLog.log(date.before(date1));

		//方法3 使用compareTo() 方法，它是由 Comparable 接口定义的，Date 类实现了这个接口 大于返回1 小于返回-1
		DealLog.log(date.compareTo(date1));

		//172800000
		//false
		//1
	}

	/**
	 * 格式化时间
	 * <p>
	 * G	纪元标记	                AD
	 * y	四位年份	                2001
	 * M	月份	                    July or 07
	 * d	一个月的日期	            10
	 * h	 A.M./P.M. (1~12)格式小时	12
	 * H	一天中的小时 (0~23)	        22
	 * m	分钟数	                    30
	 * s	秒数	                    55
	 * S	毫秒数	                    234
	 * E	星期几	                    Tuesday
	 * D	一年中的日子	            360
	 * F	一个月中第几周的周几	    2 (second Wed. in July)
	 * w	一年中第几周	            40
	 * W	一个月中第几周	            1
	 * a	A.M./P.M. 标记	            PM
	 * k	一天中的小时(1~24)	        24
	 * K	 A.M./P.M. (0~11)格式小时	10
	 * z	时区	Eastern Standard    Time
	 * '	文字定界符	                Delimiter
	 * "	单引号	                    `
	 */
	private static void c2() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		DealLog.log("当前时间为: " + ft.format(dNow));

		//当前时间为: 2019-11-02 07:25:36
	}

	/**
	 * printf格式化日期
	 * <p>
	 * c  包括全部日期和时间信息         星期六 十月 27 14:21:20 CST 2007
	 * F  "年-月-日"格式                 2007-10-27
	 * D  "月/日/年"格式                 10/27/07
	 * r  "HH:MM:SS PM"格式（12时制）    02:25:51 下午
	 * T  "HH:MM:SS"格式（24时制）       14:28:16
	 * R  "HH:MM"格式（24时制）          14:28
	 */
	private static void c3() {
		// 初始化 Date 对象
		Date date = new Date();

		//c的使用
		System.out.printf("全部日期和时间信息：%tc%n", date);
		//f的使用
		System.out.printf("年-月-日格式：%tF%n", date);
		//d的使用
		System.out.printf("月/日/年格式：%tD%n", date);
		//r的使用
		System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
		//t的使用
		System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
		//R的使用
		System.out.printf("HH:MM格式（24时制）：%tR", date);

		//全部日期和时间信息：星期一 九月 10 10:43:36 CST 2012
		//年-月-日格式：2012-09-10
		//月/日/年格式：09/10/12
		//HH:MM:SS PM格式（12时制）：10:43:36 上午
		//HH:MM:SS格式（24时制）：10:43:36
		//HH:MM格式（24时制）：10:43


		//如果你需要重复提供日期，那么利用这种方式来格式化它的每一部分就有点复杂了。
		// 因此，可以利用一个格式化字符串指出要被格式化的参数的索引。
		//索引必须紧跟在%后面，而且必须以$结束。

		// 使用toString()显示日期和时间
		System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);

		//Due date: February 09, 2014

		//或者，你可以使用 < 标志。它表明先前被格式化的参数要被再次使用。
		// 显示格式化时间
		System.out.printf("%s %tB %<te, %<tY", "Due date:", date);

		//Due date: February 09, 2014

		//定义日期格式的转换符可以使日期通过指定的转换符生成新字符串。

		//b的使用，月份简称
		String str = String.format(Locale.US, "英文月份简称：%tb", date);
		DealLog.log(str);
		System.out.printf("本地月份简称：%tb%n", date);
		//B的使用，月份全称
		str = String.format(Locale.US, "英文月份全称：%tB", date);
		DealLog.log(str);
		System.out.printf("本地月份全称：%tB%n", date);
		//a的使用，星期简称
		str = String.format(Locale.US, "英文星期的简称：%ta", date);
		DealLog.log(str);
		//A的使用，星期全称
		System.out.printf("本地星期的简称：%tA%n", date);
		//C的使用，年前两位
		System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n", date);
		//y的使用，年后两位
		System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n", date);
		//j的使用，一年的天数
		System.out.printf("一年中的天数（即年的第几天）：%tj%n", date);
		//m的使用，月份
		System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n", date);
		//d的使用，日（二位，不够补零）
		System.out.printf("两位数字的日（不足两位前面补0）：%td%n", date);
		//e的使用，日（一位不补零）
		System.out.printf("月份的日（前面不补0）：%te", date);
		DealLog.log();

		//英文月份简称：May
		//本地月份简称：五月
		//英文月份全称：May
		//本地月份全称：五月
		//英文星期的简称：Thu
		//本地星期的简称：星期四
		//年的前两位数字（不足两位前面补0）：20
		//年的后两位数字（不足两位前面补0）：17
		//一年中的天数（即年的第几天）：124
		//两位数字的月份（不足两位前面补0）：05
		//两位数字的日（不足两位前面补0）：04
		//月份的日（前面不补0）：4

	}

	/**
	 * 解析字符串为时间
	 * <p>
	 * SimpleDateFormat 类有一些附加的方法，特别是parse()，
	 * 它试图按照给定的SimpleDateFormat 对象的格式化存储来解析字符串。
	 */
	private static void c4() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String input = "1818-11-11";

		DealLog.log(input + " 解析结果 ");
		Date t;
		try {
			t = ft.parse(input);
			DealLog.log(t);
		} catch (ParseException e) {
			DealLog.log("异常解析 " + ft);
		}
		DealLog.log();

		//1818-11-11 解析结果 Wed Nov 11 00:00:00 CST 1818
	}

	/**
	 * 休眠(sleep)
	 * <p>
	 * java程序通过线程休眠,使执行时间增加
	 */
	private static void c5() {
		try {
			DealLog.log(new Date() + "\n");
			// 休眠3秒
			Thread.sleep(1000 * 3);
			DealLog.log(new Date() + "\n");
		} catch (Exception e) {
			DealLog.log("有一个例外!");
		}
		DealLog.log();

		//Sat Nov 02 21:20:27 CST 2019
		//Sat Nov 02 21:20:30 CST 2019
	}

	/**
	 * 测量时间
	 * <p>
	 * java程序通过线程休眠,使执行时间增加
	 */
	private static void c6() {
		try {
			long start = System.currentTimeMillis();
			DealLog.log(new Date() + "\n");
			Thread.sleep(5 * 60 * 10);
			DealLog.log(new Date() + "\n");
			long end = System.currentTimeMillis();
			long diff = end - start;
			DealLog.log("时间间隔 : " + diff);
		} catch (Exception e) {
			DealLog.log("一个异常!");
		}

		// 时间间隔 : 3000
	}
}
