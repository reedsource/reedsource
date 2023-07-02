/*
 * FileName: Salary
 * Author:   reedsource
 */
package main.java.k09多态.多态;

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
class Salary extends Employee {
    private double salary; // 全年工资

    public Salary(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }

    @Override
    public void mailCheck() {
        DealLog.log("Salary 类的 mailCheck 方法 ");
        DealLog.log("邮寄支票给：" + getName()
                + " ，工资为：" + salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double newSalary) {
        if (newSalary >= 0.0) {
            salary = newSalary;
        }
    }

    public double computePay() {
        DealLog.log("计算工资，付给：" + getName());
        return salary / 52;
    }
}
