/*
 * FileName: BankDemo
 * Author:   reedbook
 */
package k15异常;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈自定义异常实现〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class BankDemo {
	public static void main(String[] args) {
		CheckingAccount c = new CheckingAccount(101);
		DealLog.log("Depositing $500...");
		c.deposit(500.00);
		try {
			DealLog.log("\nWithdrawing $100...");
			c.withdraw(100.00);
			DealLog.log("\nWithdrawing $600...");
			c.withdraw(600.00);
		} catch (InsufficientFundsException e) {
			DealLog.log("Sorry, but you are short $"
					+ e.getAmount());
			e.printStackTrace();
		}

		//Depositing $500...
		//
		//Withdrawing $100...
		//
		//Withdrawing $600...
		//Sorry, but you are short $200.0
		//InsufficientFundsException
		//        at CheckingAccount.withdraw(CheckingAccount.java:25)
		//        at BankDemo.main(BankDemo.java:13)
	}
}
