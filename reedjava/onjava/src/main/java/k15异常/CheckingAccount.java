/*
 * FileName: CheckingAccount
 * Author:   reedsource
 */
package k15异常;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class CheckingAccount {
	//balance为余额，number为卡号
	private double balance;
	private int number;

	public CheckingAccount(int number) {
		this.number = number;
	}

	//方法：存钱
	public void deposit(double amount) {
		balance += amount;
	}

	//方法：取钱
	public void withdraw(double amount) throws
			InsufficientFundsException {
		if (amount <= balance) {
			balance -= amount;
		} else {
			double needs = amount - balance;
			throw new InsufficientFundsException(needs);
		}
	}

	//方法：返回余额
	public double getBalance() {
		return balance;
	}

	//方法：返回卡号
	public int getNumber() {
		return number;
	}
}
