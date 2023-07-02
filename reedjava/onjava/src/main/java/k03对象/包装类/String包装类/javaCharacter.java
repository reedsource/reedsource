/*
 * FileName: javaCharacter
 * Author:   reedsource
 */
package main.java.k03对象.包装类.String包装类;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java字符包装类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaCharacter {
    public static void main(String[] args) {
        // 原始字符 'a' 装箱到 Character 对象 ch 中
        Character ch = 'a';

        // 返回拆箱的值到 'c'
        char c = ch;

        c0();
        c1();
    }

/*
1	isLetter()      是否是一个字母
2	isDigit()       是否是一个数字字符
3	isWhitespace()  是否是一个空白字符
4	isUpperCase()   是否是大写字母
5	isLowerCase()   是否是小写字母
6	toUpperCase()   指定字母的大写形式
7	toLowerCase()   指定字母的小写形式
8	toString()      返回字符的字符串形式，字符串的长度仅为1
*/

    /**
     * 提取字符中的大小写字母
     */
    private static void c0() {
        String StrA = "I am Tom.I am from China.";
        String StrB = "";
        String StrC = "";
        for (int i = 0; i < StrA.length(); i++) {
            if (Character.isUpperCase(StrA.charAt(i))) {
                StrB += StrA.charAt(i) + "  ";
            }
            if (Character.isLowerCase(StrA.charAt(i))) {
                StrC += StrA.charAt(i) + "  ";
            }
        }
        DealLog.log("字符串中大写字母有：" + StrB);
        DealLog.log("字符串中小写字母有：" + StrC);
    }

    /**
     * 字母转大小写
     */
    private static void c1() {
        String str = "aaaaaBBcccc";
        DealLog.log("全部大写 " + str.toUpperCase());
        DealLog.log("全部小写 " + str.toLowerCase());
    }
}
