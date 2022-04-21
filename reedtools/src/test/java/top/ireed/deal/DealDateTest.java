/*
 * FileName: DealDateTest
 * Author:   reedsource
 */
package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.general.TopException;

import java.util.Date;

import static top.ireed.general.TopConstant.*;

/**
 * 功能简述:
 * 〈DealDate测试类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 18:27
 * reedsource@189.cn
 */
public class DealDateTest {
	private Date date = DealDate.getDate("2020-09-10 12:10:10 100", YYYY_MM_DD_HH_MM_SS_SSS);

	public DealDateTest() throws TopException {
	}

	@Test
	public void getYear() {
		//获取年
		Assert.assertEquals(2020, DealDate.getYear(date));
	}

	@Test
	public void getSqliteDate() {
		//格式化并 返回一个sqlite数据库支持的时间文本
		Assert.assertEquals("2020-09-10 12:10:10.100", DealDate.getSqliteDate(date));
	}

	@Test
	public void getChangeDateDay() {
		//获取时间天的开始或结束毫秒
		Assert.assertEquals("2020-09-10 00:00:00 000", DealDate.formatDateString(DealDate.getChangeDateDay(date, 0), YYYY_MM_DD_HH_MM_SS_SSS));
		Assert.assertEquals("2020-09-10 23:59:59 999", DealDate.formatDateString(DealDate.getChangeDateDay(date, 1), YYYY_MM_DD_HH_MM_SS_SSS));

	}

	@Test
	public void getChangeDate() throws TopException {
		//时间间隔 周期 年 间隔 1
		Assert.assertEquals("2021-09-10", DealDate.formatDateString(DealDate.getChangeDate(date, 0, 1), YYYY_MM_DD));
		//时间间隔 周期 月 间隔 2
		Assert.assertEquals("2020-11-10", DealDate.formatDateString(DealDate.getChangeDate(date, 1, 2), YYYY_MM_DD));
		//时间间隔 周期 日 间隔 3
		Assert.assertEquals("2020-09-13", DealDate.formatDateString(DealDate.getChangeDate(date, 2, 3), YYYY_MM_DD));
		//时间间隔 周期 时 间隔 4
		Assert.assertEquals("2020-09-10 16:10:10", DealDate.formatDateString(DealDate.getChangeDate(date, 3, 4), YYYY_MM_DD_HH_MM_SS));
		//时间间隔 周期 分 间隔 5
		Assert.assertEquals("2020-09-10 12:15:10", DealDate.formatDateString(DealDate.getChangeDate(date, 4, 5), YYYY_MM_DD_HH_MM_SS));
		//时间间隔 周期 秒 间隔 5
		Assert.assertEquals("2020-09-10 12:10:15", DealDate.formatDateString(DealDate.getChangeDate(date, 5, 5), YYYY_MM_DD_HH_MM_SS));
		//时间间隔 周期 毫秒 间隔 5
		Assert.assertEquals("2020-09-10 12:10:10 105", DealDate.formatDateString(DealDate.getChangeDate(date, 6, 5), YYYY_MM_DD_HH_MM_SS_SSS));
		//时间间隔 周期 周 间隔 1
		Assert.assertEquals("2020-09-17", DealDate.formatDateString(DealDate.getChangeDate(date, 7, 1), YYYY_MM_DD));
	}

	@Test
	public void getWeekOfYear() {
		//取得一年中的第几周
		Assert.assertEquals(37, DealDate.getWeekOfYear(date));
	}

	@Test
	public void getDateOfCurrentWeek() throws TopException {
		//获取下一周的周一时间
		Assert.assertEquals("2020-09-14", DealDate.formatDateString(DealDate.getDateOfCurrentWeek(date, 1, 1), YYYY_MM_DD));
	}

	@Test
	public void getDateWeek() {
		//获取某个日期的星期西方数值，星期日返回1，依此类推
		Assert.assertEquals(5, DealDate.getDateWeek(date));
	}

	@Test
	public void getDateChinaWeek() {
		//获取当前周几文本
		Assert.assertEquals("四", DealDate.getDateChinaWeek(date));
	}

	@Test
	public void getDate() throws TopException {
		//创建格式时间及时间格式化
		Assert.assertEquals("2018-06-05 12:13:14 000", DealDate.formatDateString(DealDate.getDate("2018-06-05 12:13:14", YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS_SSS));
	}

	@Test
	public void getDate1() {
		Assert.assertEquals("2020-09-10 12:10:10 100", DealDate.formatDateString(DealDate.getDate(2020, 9, 10, 12, 10, 10, 100), YYYY_MM_DD_HH_MM_SS_SSS));
	}

	@Test
	public void getMaxDayOfMonth() {
		//取得一个月的总天数
		Assert.assertEquals(30, DealDate.getMaxDayOfMonth(2020, 10));
	}

	@Test
	public void getTime() {
		//根据日期对象来获取日期中的时间(HH:mm:ss)
		Assert.assertEquals("12:10:10", DealDate.getTime(date));
	}

	@Test
	public void get17Time() {
		//获取yyyyMMddHHmmssSSS的17位的截止毫秒的时间
		Assert.assertTrue(!DealDate.get17Time().isEmpty());
	}

	@Test
	public void get8Time() {
		//获取yyyyMMdd的8位的截止毫秒的时间
		Assert.assertTrue(!DealDate.get8Time().isEmpty());
	}

	@Test
	public void isDateString() {
		//判断字符串是不是一个合法的日期格式
		Assert.assertFalse(DealDate.isDateString("2020年9月10日", YYYY_MM_DD));
	}

	@Test
	public void isDateQuarterStart() {
		//判断是否是季度初
		Assert.assertFalse(DealDate.isDateQuarterStart(date));
	}

	@Test
	public void isDateQuarterEnd() {

		//判断是否是季度末
		Assert.assertTrue(DealDate.isDateQuarterEnd(date));
	}

	@Test
	public void isLeapYear() {
		Assert.assertTrue(DealDate.isLeapYear(2020));
	}

	@Test
	public void formatDateString() {
		Assert.assertNotNull(DealDate.formatDateString(new Date(), YYYY_MM_DD_HH_MM_SS_SSS));
	}

	@Test
	public void getMm() {
		Assert.assertNotNull(DealDate.getMm());
	}

	@Test
	public void getDayEndDate() {
		Assert.assertNotNull(DealDate.getDayEndDate());
	}

	@Test
	public void getDayStartDate() {
		Assert.assertNotNull(DealDate.getDayStartDate());
	}

	@Test
	public void interval() throws TopException {
		//初始时间到结束时间的时间间隔 天
		Assert.assertEquals(9524, DealDate.interval(DealDate.getDate("1994-08-14", YYYY_MM_DD), date));
	}

	/**
	 * 专用于开始时间和结束时间的处理校验 测试
	 */
	@Test
	public void getDateDayBeginEnd() throws TopException {
		//获取当前时间的毫秒数开始时间
		long startTime = System.currentTimeMillis();
		DealDate dealDate = DealDate.getDateDayBeginEnd("2020-09-10 00:00:00", "2020-09-10 00:00:00", true, 2, 3);
		Assert.assertEquals("2020-09-10 00:00:00.000", DealDate.getSqliteDate(dealDate.getBeginDate()));

		DealDate dealDate1 = DealDate.getDateDayBeginEnd("", "", true, 2, 3);
		DealLog.log("今天2天后的开始时间", DealDate.getSqliteDate(dealDate1.getBeginDate()));
		DealLog.log("今天3天后的开始时间", DealDate.getSqliteDate(dealDate1.getEndDate()));

		//获取当前时间的毫秒数ms结束时间
		long endTime = System.currentTimeMillis();
		DealLog.log("以毫秒为单位程序运行时间： " + (endTime - startTime) + "ms");
	}

	@Test
	public void test() throws TopException {
		DealDate dealDate = new DealDate("2021-02-02","2022-02-02");
		dealDate=new DealDate(new Date(),new Date(),"信息");
		dealDate=new DealDate("信息");

		Assert.assertNotNull(DealDate.getMm());
	}
}
