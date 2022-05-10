/*
 * FileName: Salary
 * Author:   reedsource
 */
package k03对象.抽象类;

import top.ireed.deal.DealLog;

/**
 * 继承抽象类
 * 我们能通过一般的方法继承Employee类
 */
class Salary extends Employee {
	private double salary; //Annual salary

	public Salary(String name, String address, int number, double salary) {
		super(name, address, number);
		setSalary(salary);
	}

	@Override
	public void mailCheck() {
		DealLog.log("Within mailCheck of Salary class ");
		DealLog.log("Mailing check to " + getName()
				+ " with salary " + salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double newSalary) {
		if (newSalary >= 0.0) {
			salary = newSalary;
		}
	}

	@Override
	public double computePay() {
		DealLog.log("Computing salary pay for " + getName());
		return salary / 52;
	}
}
