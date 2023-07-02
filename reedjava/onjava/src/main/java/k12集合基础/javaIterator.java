/*
 * FileName: javaIterator
 * Author:   reedsource
 */
package main.java.k12集合基础;

import top.ireed.deal.DealLog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 功能简述:
 * 〈迭代器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 * <p>
 * 集合算法
 * 集合框架定义了几种算法，可用于集合和映射。这些算法被定义为集合类的静态方法。
 * <p>
 * 在尝试比较不兼容的类型时，一些方法能够抛出 ClassCastException异常。当试图修改一个不可修改的集合时，抛出UnsupportedOperationException异常。
 * <p>
 * 集合定义三个静态的变量：EMPTY_SET，EMPTY_LIST，EMPTY_MAP的。这些变量都不可改变。
 * <p>
 * 序号	算法描述
 * 1	Collection Algorithms
 * 这里是一个列表中的所有算法实现。
 * 如何使用迭代器
 * 通常情况下，你会希望遍历一个集合中的元素。例如，显示集合中的每个元素。
 * <p>
 * 一般遍历数组都是采用for循环或者增强for，这两个方法也可以用在集合框架，但是还有一种方法是采用迭代器遍历集合框架，它是一个对象，实现了Iterator 接口或ListIterator接口。
 * <p>
 * 迭代器，使你能够通过循环来得到或删除集合的元素。ListIterator 继承了Iterator，以允许双向遍历列表和修改元素。
 * <p>
 * 序号	迭代器方法描述
 * 1	使用 Java Iterator
 * 这里通过实例列出Iterator和listIterator接口提供的所有方法。
 */
public class javaIterator {
    public static void main(String[] args) {

    }

    /**
     * 遍历 ArrayList
     */
    private static void c0() {

    }

    /**
     * 遍历 Map
     */
    private static void c1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        DealLog.log("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            DealLog.log("key= " + key + " and value= " + map.get(key));
        }

        //第二种
        DealLog.log("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            DealLog.log("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        DealLog.log("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            DealLog.log("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        DealLog.log("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            DealLog.log("value= " + v);
        }
    }

	/*
	如何使用比较器
    TreeSet和TreeMap的按照排序顺序来存储元素. 然而，这是通过比较器来精确定义按照什么样的排序顺序。

    这个接口可以让我们以不同的方式来排序一个集合。

    序号	比较器方法描述
    1	使用 Java Comparator
    这里通过实例列出Comparator接口提供的所有方法
    总结
    Java集合框架为程序员提供了预先包装的数据结构和算法来操纵他们。

    集合是一个对象，可容纳其他对象的引用。集合接口声明对每一种类型的集合可以执行的操作。

    集合框架的类和接口均在java.util包中。

    任何对象加入集合类后，自动转变为Object类型，所以在取出的时候，需要进行强制类型转换。
	*/
}

