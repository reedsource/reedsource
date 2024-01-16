/*
 * Author:   reedsource
 */
package main.java.k12集合;

import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/5 11:19
 * reedsource@189.cn
 */
public class list与array互转 {
    public static void main(String[] args) {

        // array 转 list
        //方法1
        List<String> listA = Arrays.asList("dog", "cat", "cow");
        String[] str = {"dog", "cat", "cow"};
        List<String> listB = Arrays.asList(str);
        DealLog.log(listA);
        DealLog.log(listB);

        //方法2
        int[] a = {1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<>();
        for (int i : a) {
            list.add(i);
        }
        DealLog.log(list);

        //list 转  array
        List<String> list1 = new ArrayList<>();
        list1.add("dog");
        list1.add("cat");
        list1.add("cow");
        String[] animals = list1.toArray(new String[0]);
        for (String animal : animals) {
            DealLog.log(animal);
        }
    }
}
