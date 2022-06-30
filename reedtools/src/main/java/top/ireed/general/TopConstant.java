package top.ireed.general;/*
 * FileName: top.ireed.general.TopConstant
 * Author:   reedsource
 */

/**
 * 功能简述:
 * 〈常量表,保存全部魔法值〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/5/21 8:31
 * reedsource@189.cn
 */
public class TopConstant {
	private TopConstant() {
	}

	/****************************通用部分*************************/
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final boolean TRUE = true;
	public static final boolean FALSE = false;
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";
	/****************************返回信息部分*************************/
	/**
	 * 返回错误信息
	 */
	public static final String ERROR_MSG = "系统繁忙，请稍候再试";
	/**
	 * 超时信息
	 */
	public static final String TIMEOUT_MSG = "请求超时，请稍候再试";
	/**
	 * 返回成功信息
	 */
	public static final String SUCCESS_MSG = "SUCCESS";
	/**
	 * 返回成功信息
	 */
	public static final String OK_MSG = "ok";
	/**
	 * 成功信息标识
	 */
	public static final int SUCCESS_CODE = 0;
	/**
	 * 失败信息标识
	 */
	public static final int FAIL_CODE = -1;

	/****************************审核信息部分*************************/
	/**
	 * 未审核
	 */
	public static final String STATE_INIT = "INIT";
	/**
	 * 审核通过
	 */
	public static final String STATE_APPROVAL = "APPROVAL";
	/**
	 * 审核未通过
	 */
	public static final String STATE_REJECT = "REJECT";

	/****************************时间部分*************************/
	/**
	 * 毫秒
	 */
	public static final String PERIOD_MILLISECOND = "MILLISECOND";
	/**
	 * 秒
	 */
	public static final String PERIOD_SECOND = "SECOND";
	/**
	 * 分
	 */
	public static final String PERIOD_MIN = "MIN";
	/**
	 * 时
	 */
	public static final String PERIOD_HOUR = "HOUR";
	/**
	 * 日
	 */
	public static final String PERIOD_DAY = "DAY";
	/**
	 * 周
	 */
	public static final String PERIOD_WEEK = "WEEK";
	/**
	 * 月
	 */
	public static final String PERIOD_MONTH = "MONTH";
	/**
	 * 季度
	 */
	public static final String PERIOD_QUARTER = "QUARTER";
	/**
	 * 年
	 */
	public static final String PERIOD_YEAR = "YEAR";
	/**
	 * 格式化部分
	 * sqlite秒与毫秒间隔.
	 */
	public static final String SQLITE_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	/*
	 * 英文时间格式化
	 * */
	public static final String EEEMMMDD = "EEE MMM dd hh:mm:ss zzz yyyy";

	/****************************字符串部分*************************/
	public static final String S_A = "a";
	public static final String S_B = "b";
	public static final String S_C = "c";
	public static final String S_M = "m";
	public static final String S_N = "n";
	public static final String S_1 = "1";
	/****************************符号部分*************************/
	public static final String F_W = "?";
	public static final String F_L = "&";
	public static final String F_B = "%";
	public static final String F_F = ";";
	public static final String F_X_Z = "(";
	public static final String F_X_Y = ")";
	public static final String F_D_Z = "{";
	public static final String F_D_Y = "}";

	/****************************数值部分*************************/
	public static final int INT0 = 0;
	public static final int INT1 = 1;
	public static final int INT2 = 2;
	public static final int INT3 = 3;
	public static final int INT4 = 4;
	public static final int INT5 = 5;
	public static final int INT6 = 6;
	public static final int INT7 = 7;
	public static final int INT8 = 8;
	public static final int INT9 = 9;
	public static final int INT10 = 10;
	public static final int INT11 = 11;
	public static final int INT12 = 12;
	public static final int INT13 = 13;
	public static final int INT14 = 14;
	public static final int INT15 = 15;
	public static final int INT16 = 16;
	public static final int INT17 = 17;
	public static final int INT18 = 18;
	public static final int INT19 = 19;
	public static final int INT20 = 20;
	public static final int INT21 = 21;
	public static final int INT22 = 22;
	public static final int INT23 = 23;
	public static final int INT24 = 24;
	public static final int INT25 = 25;
	public static final int INT26 = 26;
	public static final int INT27 = 27;
	public static final int INT28 = 28;
	public static final int INT29 = 29;
	public static final int INT30 = 30;
	public static final int INT31 = 31;
	public static final int INT32 = 32;
	public static final int INT33 = 33;
	public static final int INT34 = 34;
	public static final int INT35 = 35;
	public static final int INT36 = 36;
	public static final int INT37 = 37;
	public static final int INT38 = 38;
	public static final int INT39 = 39;
	public static final int INT40 = 40;
	public static final int INT41 = 41;
	public static final int INT42 = 42;
	public static final int INT43 = 43;
	public static final int INT44 = 44;
	public static final int INT45 = 45;
	public static final int INT46 = 46;
	public static final int INT47 = 47;
	public static final int INT48 = 48;
	public static final int INT49 = 49;
	public static final int INT50 = 50;
	public static final int INT51 = 51;
	public static final int INT52 = 52;
	public static final int INT53 = 53;
	public static final int INT54 = 54;
	public static final int INT55 = 55;
	public static final int INT56 = 56;
	public static final int INT57 = 57;
	public static final int INT58 = 58;
	public static final int INT59 = 59;
	public static final int INT60 = 60;
	public static final int INT61 = 61;
	public static final int INT62 = 62;
	public static final int INT63 = 63;
	public static final int INT64 = 64;
	public static final int INT65 = 65;
	public static final int INT66 = 66;
	public static final int INT67 = 67;
	public static final int INT68 = 68;
	public static final int INT69 = 69;
	public static final int INT70 = 70;
	public static final int INT71 = 71;
	public static final int INT72 = 72;
	public static final int INT73 = 73;
	public static final int INT74 = 74;
	public static final int INT75 = 75;
	public static final int INT76 = 76;
	public static final int INT77 = 77;
	public static final int INT78 = 78;
	public static final int INT79 = 79;
	public static final int INT80 = 80;
	public static final int INT81 = 81;
	public static final int INT82 = 82;
	public static final int INT83 = 83;
	public static final int INT84 = 84;
	public static final int INT85 = 85;
	public static final int INT86 = 86;
	public static final int INT87 = 87;
	public static final int INT88 = 88;
	public static final int INT89 = 89;
	public static final int INT90 = 90;
	public static final int INT91 = 91;
	public static final int INT92 = 92;
	public static final int INT93 = 93;
	public static final int INT94 = 94;
	public static final int INT95 = 95;
	public static final int INT96 = 96;
	public static final int INT97 = 97;
	public static final int INT98 = 98;
	public static final int INT99 = 99;
	public static final int INT100 = 100;
	public static final int INT200 = 200;
	public static final int INT500 = 500;
	public static final int INT1000 = 1000;
	public static final int INT10000 = 10000;
}
