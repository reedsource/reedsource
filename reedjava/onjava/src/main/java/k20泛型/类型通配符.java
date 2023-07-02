/*
 * FileName: 类型通配符
 * Author:   reedsource
 */
package main.java.k20泛型;

import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈java类型通配符〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 * <p>
 * 1、类型通配符一般是使用?代替具体的类型参数。例如 List<?> 在逻辑上是List<String>,List<Integer> 等所有List<具体类型实参>的父类。
 * 解析： 因为getData()方法的参数是List类型的，所以name，age，number都可以作为这个方法的实参，这就是通配符的作用
 */
public class 类型通配符 {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Number> number = new ArrayList<>();

        name.add("icon");
        age.add(18);
        number.add(314);

        getList通用泛型(name);
        getList通用泛型(age);
        getList通用泛型(number);

        // 2、类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型。
        //这里报错
        //getListNumber专用泛型(name);
        getListNumber专用泛型(age);
        getListNumber专用泛型(number);

        //data :icon
        //data :18
        //data :314

        //data :18
        //data :314

    }

    public static void getList通用泛型(List<?> data) {
        DealLog.log("data :" + data.get(0));
    }

    public static void getListNumber专用泛型(List<? extends Number> data) {
        DealLog.log("data :" + data.get(0));
    }

    //解析： 在(//1)处会出现错误，因为getListNumber专用泛型()方法中的参数已经限定了参数泛型上限为Number，所以泛型为String是不在这个范围之内，所以会报错
    //3、类型通配符下限通过形如 List<? super Number>来定义，表示类型只能接受Number及其三层父类类型，如 Object 类型的实例。
}
