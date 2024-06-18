package main.集合;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Set交集差集 {


    @Test
    public void set合并() {
        // 创建两个集合
        Set<Integer> setA = new HashSet<>();
        // 向集合中添加元素
        setA.add(1);
        setA.add(2);
        setA.add(3);

        Set<Integer> setB = new HashSet<>();
        setB.add(2);
        setB.add(3);
        setB.add(4);


        setA.addAll(setB);

        //[2, 3]
        System.out.println(setA);
    }


    @Test
    public void set交集() {
        // 创建两个集合
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        // 向集合中添加元素
        setA.add(1);
        setA.add(2);
        setA.add(3);

        setB.add(2);
        setB.add(3);
        setB.add(4);

        setA.retainAll(setB);

        //[2, 3]
        System.out.println(setA);
    }


    @Test
    public void set差集交集() {
        // 创建两个集合
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        // 向集合中添加元素
        setA.add(1);
        setA.add(2);
        setA.add(3);

        setB.add(2);
        setB.add(3);
        setB.add(4);

        // 计算差集 setA - setB
        setA.removeAll(setB);

        // 打印结果 [1]  忽略 setB存在而setA不存在的数据 4
        System.out.println("Set A - Set B: " + setA);
    }
}