/*
 * FileName: JavaReferenceNew
 * Author:   reedsource
 */
package main.java.k03对象.引用类型;

import k03对象.对象的基本操作.Dog;
import org.junit.Test;
import top.ireed.deal.DealLog;


/**
 * 功能简述:
 * 〈引用类型new解析〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/12 23:41
 * reedsource@189.cn
 */
public class JavaReferenceNew {
    /**
     * 引用类型是一个对象类型，它的值是指向内存空间的引用，就是地址，
     * 所指向的内存中保存着变量所表示的一个值或一组值
     */
    @Test
    public void test() {
        //这句话是2步，首先执行new Dog（），给today变量开辟数据空间，
        // 然后再执行赋值操作
        Dog dog = new Dog();

        DealLog.log(dog);
        //引用类型变量在声明后必须通过实例化开辟数据空间，才能对变量所指向的对象进行访问
        //变量分配了引用空间，数据空间没有分配，因为不知道数据是什么,会发生错误

        //在内存开辟两个引用空间
        Dog a;
        Dog b;
        // 开辟Dog对象的数据空间，并把该空间的首地址赋给a
        a = new Dog();
        // 将a存储空间中的地址写到b的存储空间中
        b = a;

        DealLog.log(b);
    }
}
