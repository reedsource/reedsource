/*
 * FileName: Employee
 * Author:   reedsource
 */
package k03对象.抽象类;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/10 20:16
 * reedsource@189.cn
 */
abstract class Employee {
	private String name;
	private String address;
	private int number;

	public Employee(String name, String address, int number) {
		DealLog.log("Constructing an Employee");
		this.name = name;
		this.address = address;
		this.number = number;
	}

	public double computePay() {
		DealLog.log("Inside Employee computePay");
		return 0.0;
	}

	public void mailCheck() {
		DealLog.log("Mailing a check to " + this.name
				+ " " + this.address);
	}

	@Override
	public String toString() {
		return name + " " + address + " " + number;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String newAddress) {
		address = newAddress;
	}

	public int getNumber() {
		return number;
	}
}
