package top.ireed.deal;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import top.ireed.general.TopException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.util.Calendar.*;
import static top.ireed.general.TopConstant.*;

/**
 * 功能简述:
 * 〈时间处理工具类〉
 *
 * @author ireed
 * @version 1.0.0
 * date 2018/8/17 12:01
 * reedsource@189.cn
 */
public class DealDate {

	/**
	 * 月份天数常量
	 */
	private static final int[] DAY_OF_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String[] DAY_OF_WEEK = new String[]{"日", "一", "二", "三", "四", "五", "六"};

	//===构造方法===================================================================================================

	/**
	 * 开始时间
	 */
	private Date beginDate;
	/**
	 * 结束时间
	 */
	private Date endDate;

	/**
	 * 错误信息
	 */
	private String dateMsg;

	/**
	 * 创建时间类
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 */
	public DealDate(Date beginDate, Date endDate) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.dateMsg = null;
	}

	/**
	 * 创建时间类
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 */
	public DealDate(String beginDate, String endDate) throws TopException {
		this.beginDate = getDate(beginDate);
		this.endDate = getDate(endDate);
		this.dateMsg = null;
	}

	/**
	 * 创建时间类
	 *
	 * @param beginDate 开始时间
	 * @param endDate   结束时间
	 * @param dateMsg   错误信息
	 */
	public DealDate(Date beginDate, Date endDate, String dateMsg) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.dateMsg = dateMsg;
	}

	/**
	 * 异常信息返回
	 *
	 * @param dateMsg 异常信息
	 */
	public DealDate(String dateMsg) {
		this.dateMsg = dateMsg;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDateMsg() {
		return dateMsg;
	}

	public void setDateMsg(String dateMsg) {
		this.dateMsg = dateMsg;
	}

	private DealDate() {
		super();
	}


	/**
	 * 专用于开始时间和结束时间的处理校验
	 * <p>
	 * 如果开始时间和结束时间不为空 返回时间
	 * 如果有一个为空 返回以当前时间为基础符合日间隔的开始结束时间
	 *
	 * @param beginDate        开始时间
	 * @param endDate          结束时间
	 * @param beginDateUndergo 开始时间增减 前正负后 当天为 0
	 * @param endDateUndergo   结束时间增减 前正负后 当次为 0
	 * @return 实体
	 */
	public static DealDate getDateDayBeginEnd(Date beginDate, Date endDate, boolean change, int beginDateUndergo, int endDateUndergo) throws TopException {
		return getDateDayBeginEnd(formatDateString(beginDate), formatDateString(endDate), change, beginDateUndergo, endDateUndergo);
	}

	/**
	 * 专用于开始时间和结束时间的处理校验
	 * <p>
	 * 如果开始时间和结束时间不为空 返回时间
	 * 都为空 且开启时间间隔 返回间隔天数时间
	 * 开始时间大于结束时间 返回含错误文本的数据信息
	 *
	 * @param request          请求数据
	 * @param change           是否需要默认增减时间
	 * @param beginDateUndergo 开始时间增减 前正负后 当天为 0
	 * @param endDateUndergo   结束时间增减 前正负后 当次为 0
	 * @return 实体
	 */
	public static DealDate getDateDayBeginEnd(HttpServletRequest request, boolean change, int beginDateUndergo, int endDateUndergo) throws TopException {
		return getDateDayBeginEnd(request.getParameter("beginDate"), request.getParameter("endDate"), change, beginDateUndergo, endDateUndergo);
	}

	/**
	 * 专用于开始时间和结束时间的处理校验
	 * <p>
	 * 如果开始时间和结束时间不为空 返回时间
	 * 都为空 且开启时间间隔 返回间隔天数时间
	 * 开始时间大于结束时间 返回含错误文本的数据信息
	 *
	 * @param beginDateStr     开始时间 格式yyyy-MM-dd HH:mm:ss
	 * @param endDateStr       结束时间 格式yyyy-MM-dd HH:mm:ss
	 * @param change           是否需要默认增减时间
	 * @param beginDateUndergo 开始时间增减 前正负后 当天为 0
	 * @param endDateUndergo   结束时间增减 前正负后 当次为 0
	 * @return 实体
	 */
	public static DealDate getDateDayBeginEnd(String beginDateStr, String endDateStr, boolean change, int beginDateUndergo, int endDateUndergo) throws TopException {
		//开始时间
		Date beginDate = null;
		//结束时间
		Date endDate = null;
		//开始或结束时间 不为空 返回格式化后的时间 格式yyyy-MM-dd HH:mm:ss
		if (StrUtil.isNotBlank(beginDateStr) && StrUtil.isNotBlank(endDateStr)) {
			beginDate = DateUtil.parseDate(beginDateStr);
			endDate = DateUtil.parseDate(endDateStr);
		} else if (StrUtil.isNotBlank(beginDateStr) && StrUtil.isBlank(endDateStr)) {
			beginDate = DateUtil.parseDate(beginDateStr);
		} else if (StrUtil.isBlank(beginDateStr) && StrUtil.isNotBlank(endDateStr)) {
			endDate = DateUtil.parseDate(endDateStr);

			//开始或结束时间都为空 返回00:00:00时间 返回当日结束 59:59:59
		} else if (change) {
			beginDate = getChangeDateDay(DealDate.getChangeDate(new Date(), 2, beginDateUndergo), 0);
			endDate = getChangeDateDay(DealDate.getChangeDate(new Date(), 2, endDateUndergo), 1);
		}

		//开始时间大于结束时间,报异常
		if (beginDate != null && endDate != null && (beginDate.getTime() - endDate.getTime() > 0)) {
			return new DealDate(beginDate, endDate, "开始时间不能大于结束时间");
		}

		return new DealDate(beginDate, endDate);
	}

	//===直接调用方法===================================================================================================

	/**
	 * 获取时间年
	 *
	 * @param date 时间
	 * @return 年份
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(YEAR);
	}


	/**
	 * 格式化并 将一个时间格式字符串格式化为sqlite数据库支持的时间文本
	 *
	 * @return 时间文本
	 */
	public static String getSqliteDate(String dateString) throws TopException {
		return getSqliteDate(getDate(dateString));
	}

	/**
	 * 格式化并 返回一个sqlite数据库支持的时间文本
	 *
	 * @return 时间文本
	 */
	public static String getSqliteDate(Date date) {
		return formatDateString(date == null ? new Date() : date, SQLITE_YYYY_MM_DD_HH_MM_SS_SSS);
	}

	/**
	 * 格式化并 返回一个sqlite数据库支持的当前时间文本
	 *
	 * @return 时间文本
	 */
	public static String getSqliteDate() {
		return getSqliteDate(new Date());
	}

	/**
	 * 获取时间天的开始或结束毫秒
	 *
	 * @param date        需要获取最开始或最末的时间
	 * @param circulation 最开始0或结束1
	 * @return 天时间的开始
	 */
	public static Date getChangeDateDay(Date date, int circulation) {
		//创建日历时间对象
		Calendar calendar = Calendar.getInstance();
		//将时间赋值给日历对象
		calendar.setTime(date);
		//如果取一天的最开始毫秒
		if (circulation == 0) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			//如果取一天的最后毫秒
		} else if (circulation == 1) {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
		}
		return calendar.getTime();
	}

	/**
	 * 获取时间增减变化后的时间
	 *
	 * @param date   需要变化的时间
	 * @param period 变化周期 参数对应0年1月2日3时4分5秒6毫秒7周
	 * @param dayNum 周期相隔数 正整数表示前推 ，负整数表示后推
	 * @return 时间周期间隔后的时间
	 */
	public static Date getChangeDate(Date date, int period, int dayNum) throws TopException {
		//创建日历时间对象
		Calendar calendar = Calendar.getInstance();
		//将时间赋值给日历对象
		calendar.setTime(date);
		switch (period) {
			case 0:
				calendar.set(YEAR, calendar.get(YEAR) + dayNum);
				break;
			case 1:
				calendar.set(MONTH, calendar.get(Calendar.MONTH) + dayNum);
				break;
			case 2:
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dayNum);
				break;
			case 3:
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + dayNum);
				break;
			case 4:
				calendar.set(MINUTE, calendar.get(MINUTE) + dayNum);
				break;
			case 5:
				calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + dayNum);
				break;
			case 6:
				calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND) + dayNum);
				break;
			case 7:
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + (dayNum * 7));
				break;
			default:
				throw new TopException("时间周期变化进入时间异常 变化周期 参数对应0年1月2日3时4分5秒6毫秒7周");
		}
		return calendar.getTime();
	}

	/**
	 * 取得一年中的第几周
	 *
	 * @param date 时间
	 * @return 返回日期是当年的第几周
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取几周后(前)星期几的日期
	 *
	 * @param date      初始时间,如果不设置默认当前时间
	 * @param interval  时间间隔,本周为0,下周为1,上周为-1
	 * @param dayOfWeek 星期几的数量 周一就是1
	 * @return 时间
	 */
	public static Date getDateOfCurrentWeek(Date date, int interval, int dayOfWeek) throws TopException {
		int start = 1;
		int end = 7;

		if (dayOfWeek > end || dayOfWeek < start) {
			throw new TopException("参数必须是1-7之间的数字");
		}

		Calendar cal = Calendar.getInstance();
		//如果设定了初始时间
		if (date != null) {
			cal.setTime(date);
		}
		//将星期数值赋值给日历对象
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek + 1);
		//根据周数,增减周数的7倍天数
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + interval * 7);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}


	/**
	 * 获取某个日期的星期数值，星期日返回1，依此类推
	 *
	 * @param date 日期
	 * @return 星期几西方数值
	 */
	public static int getDateWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某个日期中文星期
	 *
	 * @param date 日期
	 * @return 日 一 二 三 四 五 六
	 */
	public static String getDateChinaWeek(Date date) {
		//数组本身-1
		return DAY_OF_WEEK[getDateWeek(date) - 1];
	}


	/**
	 * 获取指定的年, 月, 日, 时, 分, 秒等参数组合的日期对象.
	 *
	 * @param year      年
	 * @param month     月
	 * @param date      日
	 * @param hourOfDay 时(24小时制)
	 * @param minute    分
	 * @param second    秒
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - INT1, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		return cal.getTime();
	}

	/**
	 * 根据时间字符串转化为时间对象
	 * YYYY_MM_DD_HH_MM_SS
	 * YYYY_MM_DD_HH_MM
	 * YYYY_MM_DD
	 * YYYY_MM
	 * 英文时间
	 *
	 * @param dateString 时间字符串 格式 yyyy-MM-dd HH:mm:ss SSS 后面的时分秒都可以简化
	 * @return 时间对象
	 */
	public static Date getDate(String dateString, String dateFormatString) throws TopException {
		//默认转换中文时间
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatString);
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			//尝试转换英文时间
			SimpleDateFormat formatterEn = new SimpleDateFormat(dateFormatString, Locale.ENGLISH);
			try {
				return formatterEn.parse(dateString);
			} catch (ParseException e1) {
				throw new TopException("时间字符串或时间格式异常");
			}
		}
	}

	/**
	 * 根据标准时间字符串转化为时间对象
	 * YYYY_MM_DD_HH_MM_SS
	 * YYYY_MM_DD_HH_MM
	 * YYYY_MM_DD
	 * YYYY_MM
	 * 英文时间
	 *
	 * @param dateString 时间字符串 格式 yyyy-MM-dd HH:mm:ss后面的时分秒都可以简化
	 * @return 时间对象
	 */
	public static Date getDate(String dateString) throws TopException {
		try {
			return getDate(dateString, YYYY_MM_DD);
		} catch (TopException e) {
			try {
				return getDate(dateString, YYYY_MM_DD_HH_MM_SS);
			} catch (TopException e1) {
				try {
					return getDate(dateString, YYYY_MM_DD_HH_MM);
				} catch (TopException e2) {
					try {
						return getDate(dateString, YYYY_MM);
					} catch (TopException e3) {
						//尝试转换英文格式字符串
						try {
							return getDate(dateString, EEEMMMDD);
						} catch (TopException e4) {
							throw new TopException("时间字符串或时间格式异常", dateString);
						}
					}
				}
			}
		}
	}


	/**
	 * 取得一个月的总天数
	 *
	 * @param year  年份
	 * @param month 月份，0表示1月，依此类推
	 * @return 最多的天数
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 1 && isLeapYear(year)) {
			return 29;
		}
		return DAY_OF_MONTH[month];
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm:ss).
	 *
	 * @param date 日期对象
	 * @return 时间字符串, 格式为: HH:mm:ss
	 */
	public static String getTime(Date date) {
		return formatDateString(date, HH_MM_SS);
	}

	/**
	 * 获取yyyyMMddHHmmssSSS的17位的截止毫秒的时间
	 *
	 * @return yyyyMMddHHmmssSSS的17位的截止毫秒的时间
	 */
	public static String get17Time() {
		return formatDateString(new Date(), YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 获取yyyyMMdd的8位的截止毫秒的时间
	 *
	 * @return yyyyMMdd的8位的截止毫秒的时间
	 */
	public static String get8Time() {
		return formatDateString(new Date(), YYYYMMDD);
	}


	/**
	 * 判断字符串是不是一个合法的日期格式
	 *
	 * @param dateString   时间字符串
	 * @param formatString 时间格式
	 * @return 布尔
	 */
	public static boolean isDateString(String dateString, String formatString) {
		//假定转换成功为真
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(dateString);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，出现异常,就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 判断是否是季度初
	 *
	 * @param date 判断的时间
	 * @return 布尔
	 */
	public static boolean isDateQuarterStart(Date date) {
		//创建日历时间对象
		Calendar calendar = Calendar.getInstance();
		//将时间赋值给日历对象
		calendar.setTime(date);
		//取月份值
		int month = calendar.get(Calendar.MONTH) + 1;
		//除数为3,只有时间的月份除以3的余数为1时,表示是1,4,7,10,表示是季度初
		int sum = 3;
		return month % sum == 1;
	}

	/**
	 * 判断是否是季度末
	 *
	 * @param date 判断的时间
	 * @return 布尔
	 */
	public static boolean isDateQuarterEnd(Date date) {
		//创建日历时间对象
		Calendar calendar = Calendar.getInstance();
		//将时间赋值给日历对象
		calendar.setTime(date);
		//取月份值
		int month = calendar.get(Calendar.MONTH) + 1;
		//除数为3,只有时间的月份除以3的余数为0时,表示是3,6,9,12,表示是季度末
		int sum = 3;
		return month % sum == 0;
	}

	/**
	 * 判断是否是闰年
	 *
	 * @param year 年份
	 * @return 是true，否则false
	 */
	public static boolean isLeapYear(int year) {
		Calendar calendar = Calendar.getInstance();
		return ((GregorianCalendar) calendar).isLeapYear(year);
	}

/*
	对比时间大小,直接date.getTime()对比即可,不需要工具类
 */

	/**
	 * 格式话时间为字符串
	 *
	 * @param date             时间
	 * @param dateFormatString 时间格式 类似yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间格式化后的字符串
	 */
	public static String formatDateString(Date date, String dateFormatString) {
		if (date == null) {
			return null;
		}
		//创建日期-时间格式的模式对象
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatString);
		return sdf.format(date);
	}

	/**
	 * 获取标准时间格式化字符串
	 *
	 * @param date 时间
	 * @return 标准时间格式化字符串
	 */
	public static String formatDateString(Date date) {
		return formatDateString(date, YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * @return yyyy-MM-dd的模式格式时间文本
	 */
	public static String getMm() {
		return formatDateString(new Date(), YYYY_MM_DD);
	}

	/**
	 * @return 获得当天的结束时间
	 */
	public static Date getDayEndDate() {
		return getChangeDateDay(new Date(), 1);
	}

	/**
	 * @return 获得当天的开始时间
	 */
	public static Date getDayStartDate() {
		return getChangeDateDay(new Date(), 0);
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔天数
	 *
	 * @param date1 开始时间
	 * @param date2 结束时间
	 * @return 时间间隔
	 */
	public static int interval(Date date1, Date date2) {
		//向上取整
		return (int) Math.ceil((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
	}
}
