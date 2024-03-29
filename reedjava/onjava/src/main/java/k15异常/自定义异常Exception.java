/*
 * FileName: 自定义异常Exception
 * Author:   reedsource
 */
package main.java.k15异常;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 * <p>
 * 自定义异常类，继承Exception类
 */
public class 自定义异常Exception extends Exception {
    //此处的amount用来储存当出现异常（取出钱多于余额时）所缺乏的钱
    private double amount;

    public 自定义异常Exception(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
