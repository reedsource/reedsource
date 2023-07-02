/*
 * FileName: 继承抽象基类
 * Author:   reedsource
 */
package main.java.k03对象.实例化抽象类;

import top.ireed.deal.DealLog;

/**
 * 继承抽象类
 * 我们能通过一般的方法继承Employee类
 */
class 继承抽象基类 extends 抽象基类 {
    private double salary; //Annual salary

    public 继承抽象基类(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }

    @Override
    public void mailCheck() {
        DealLog.log("Within mailCheck of 继承抽象基类 class ");
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
