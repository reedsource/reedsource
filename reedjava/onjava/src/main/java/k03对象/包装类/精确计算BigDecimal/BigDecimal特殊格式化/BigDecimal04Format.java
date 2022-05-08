/*
 * FileName: BigDecimal04Format
 * Author:   reedbook
 */
package k03对象.包装类.精确计算BigDecimal.BigDecimal特殊格式化;

import top.ireed.deal.DealLog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 功能简述:
 * 〈格式化〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class BigDecimal04Format {
	public static void main(String[] args) {

		//建立货币格式化引用
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		//建立百分比格式化引用
		NumberFormat percent = NumberFormat.getPercentInstance();
		//百分比小数点最多3位
		percent.setMaximumFractionDigits(3);

		//贷款金额
		BigDecimal loanAmount = new BigDecimal("15000.48");
		//利率
		BigDecimal interestRate = new BigDecimal("0.008");
		//相乘
		BigDecimal interest = loanAmount.multiply(interestRate);

		DealLog.log("贷款金额:\t" + currency.format(loanAmount));
		DealLog.log("利率:\t" + percent.format(interestRate));
		DealLog.log("利息:\t" + currency.format(interest));


		DealLog.log(formatToNumber(new BigDecimal("3.435")));
		DealLog.log(formatToNumber(new BigDecimal(0)));
		DealLog.log(formatToNumber(new BigDecimal("0.00")));
		DealLog.log(formatToNumber(new BigDecimal("0.001")));
		DealLog.log(formatToNumber(new BigDecimal("0.006")));
		DealLog.log(formatToNumber(new BigDecimal("0.206")));
	}

	/**
	 * BigDecimal格式化保留2为小数，不足则补0：
	 * <p>
	 * 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
	 * 2.传入的参数等于0，则直接返回字符串"0.00"
	 * 3.大于1的小数，直接格式化返回字符串
	 *
	 * @param obj 传入的小数
	 * @return 转换后的字符串格式
	 */
	private static String formatToNumber(BigDecimal obj) {
		DecimalFormat df = new DecimalFormat("#.00");
		if (obj.compareTo(BigDecimal.ZERO) == 0) {
			return "0.00";
		} else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
			return "0" + df.format(obj).toString();
		} else {
			return df.format(obj).toString();
		}
	}
}
