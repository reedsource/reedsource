/*
 * FileName: switch分支
 * Author:   reedsource
 */
package main.java.k05控制流;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈分支语句〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class switch分支 {
    public static void main(String[] args) {
        c0();
    }

    /**
     * switch case 语句
     * <p>
     * switch case 语句判断一个变量与一系列值中某个值是否相等，每个值称为一个分支。
     * <p>
     * switch(expression){
     * case value :
     * //语句
     * break; //可选
     * case value :
     * //语句
     * break; //可选
     * //你可以有任意数量的case语句
     * default : //可选
     * //语句
     * }
     * <p>
     * switch 语句中的变量类型可以是： byte、short、int 或者 char。
     * 从 Java SE 7 开始，switch 支持字符串 String 类型了，同时 case 标签必须为字符串常量或字面量。
     * <p>
     * switch 语句可以拥有多个 case 语句。
     * 每个 case 后面跟一个要比较的值和冒号。
     * <p>
     * case 语句中的值的数据类型必须与变量的数据类型相同，而且只能是常量或者字面常量。
     * 当变量的值与 case 语句的值相等时，那么 case 语句之后的语句开始执行，直到 break 语句出现才会跳出 switch 语句。
     * 当遇到 break 语句时，switch 语句终止。
     * 程序跳转到 switch 语句后面的语句执行。
     * case 语句不必须要包含 break 语句。
     * 如果没有 break 语句出现，程序会继续执行下一条 case 语句，直到出现 break 语句。
     * <p>
     * switch 语句可以包含一个 default 分支，该分支一般是 switch 语句的最后一个分支（可以在任何位置，但建议在最后一个）。
     * default 在没有 case 语句的值和变量值相等的时候执行。default 分支不需要 break 语句。
     * <p>
     * switch case 执行时，一定会先进行匹配，匹配成功返回当前 case 的值，再根据是否有 break，判断是否继续输出，或是跳出判断
     * 如果 case 语句块中没有 break 语句时，JVM 并不会顺序输出每一个 case 对应的返回值，而是继续匹配，匹配不成功则返回默认 case。
     * 如果 case 语句块中没有 break 语句时，匹配成功后，从当前 case 开始，后续所有 case 的值都会输出。
     * 如果当前匹配成功的 case 语句块没有 break 语句，则从当前 case 开始，后续所有 case 的值都会输出，如果后续的 case 语句块有 break 语句则会跳出判断。
     */
    private static void c0() {
        char grade = 'C';

        //case支持 int char String
        switch (grade) {
            case 'A':
                DealLog.log("优秀");
                break;
            //BC都会输出良好
            case 'B':
            case 'C':
                DealLog.log("良好");
                break;
            case 'D':
                DealLog.log("及格");
                break;
            case 'F':
                DealLog.log("你需要再努力努力");
                break;
            default:
                DealLog.log("未知等级");
        }
        DealLog.log("你的等级是 " + grade);

        //良好
        //你的等级是 C
    }
}
