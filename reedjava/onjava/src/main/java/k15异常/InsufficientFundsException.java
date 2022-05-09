/*
 * FileName: InsufficientFundsException
 * Author:   reedbook
 */
package k15异常;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 * <p>
 * 自定义异常类，继承Exception类
 */
public class InsufficientFundsException extends Exception {
	//此处的amount用来储存当出现异常（取出钱多于余额时）所缺乏的钱
	private double amount;

	public InsufficientFundsException(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
}
