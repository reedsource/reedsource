/*
 * Author:   reedsource
 */
package main.java.k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈
 * 字面量是让程序知道输入字符类型的字面的量
 * 但是特别情况下,必须使用与该字面量相关的字符来添加额外的信息来引导编译器
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/17 20:22
 * reedsource@189.cn
 */
public class 字面量 {

    @Test
    public void 字面量_Test() {
        int i1 = 0x2f; // 16进制 (小写)
        DealLog.log("i1: " + Integer.toBinaryString(i1));
        int i2 = 0X2F; // 16进制 (大写)
        DealLog.log("i2: " + Integer.toBinaryString(i2));
        int i3 = 0177; // 八进制 (前置0)
        DealLog.log("i3: " + Integer.toBinaryString(i3));
        char c = 0xffff; // char最大十六进制值
        DealLog.log("c: " + Integer.toBinaryString(c));
        byte b = 0x7f; // byte 最大十六进制值 0111111;
        DealLog.log("b: " + Integer.toBinaryString(b));
        short s = 0x7fff; // short最大十六进制值
        DealLog.log("s: " + Integer.toBinaryString(s));
        long n1 = 200L; // long 类型后缀
        long n2 = 200l; // long 类型后缀 (令人困惑的 不建议这样写)
        long n3 = 200;
        DealLog.log(n1, n2, n3);
        // Java 7 二进制字面量:
        byte blb = (byte) 0b00110101;
        DealLog.log("blb: " + Integer.toBinaryString(blb));
        short bls = (short) 0B0010111110101111;
        DealLog.log("bls: " + Integer.toBinaryString(bls));
        int bli = 0b00101111101011111010111110101111;
        DealLog.log("bli: " + Integer.toBinaryString(bli));
        long bll = 0b00101111101011111010111110101111;
        DealLog.log("bll: " + Long.toBinaryString(bll));
        float f1 = 1;
        float f2 = 1F; // float 类型后缀
        float f3 = 1f; // float 类型后缀
        double d1 = 1d; // double 类型后缀
        double d2 = 1D; // double 类型后缀
        DealLog.log(f1, f2, f3, d1, d2);

        //3.41435936445667E8
        //101111101011111010111110101111
        //2fafafaf
        //7fe9b7aa
        //i1: 101111
        //i2: 101111
        //i3: 1111111
        //c: 1111111111111111
        //b: 1111111
        //s: 111111111111111
        //200 200 200
        //blb: 110101
        //bls: 10111110101111
        //bli: 101111101011111010111110101111
        //bll: 101111101011111010111110101111
        //1.0 1.0 1.0 1.0 1.0
    }


    /**
     * java7加入 使程序更加可读
     * 1.只能单个,不能连续多个
     * 2.数字的开头和结尾不能是下划线
     * 3.像F,D,L这样的后缀周围不能有下划线
     * 4.在二进制或十六进制标识符b和x周围不能有下划线
     */
    @Test
    public void 字面量下划线_Test() {
        double d = 341_435_936.445_667;
        DealLog.log(d);
        int bin = 0b0010_1111_1010_1111_1010_1111_1010_1111;
        DealLog.log(Integer.toBinaryString(bin));
        System.out.printf("%x%n", bin);               // [1]
        long hex = 0x7f_e9_b7_aa;
        System.out.printf("%x%n", hex);

        //3.41435936445667E8
        //101111101011111010111110101111
        //2fafafaf
        //7fe9b7aa
    }
}
