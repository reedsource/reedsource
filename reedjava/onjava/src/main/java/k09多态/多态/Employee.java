/*
 * FileName: Employee
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
class Employee {
    private String name;
    private String address;
    private int number;

    public Employee(String name, String address, int number) {
        DealLog.log("Employee 构造函数");
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void mailCheck() {
        DealLog.log("邮寄支票给： " + this.name
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
