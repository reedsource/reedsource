package main.集合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能简述:〈〉
 *
 * @author reedsource
 * date 2024/4/18 15:30
 * reedsource@189.cn
 */
public class List交集 {

    public static void main(String[] args) {


        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);


        List<Integer> intersection0 = list1.stream().filter(list2::contains).toList();
        System.out.println(intersection0);


        List<Integer> intersection1 = new ArrayList<>();
        for (Integer item : list1) {
            if (list2.contains(item)) {
                intersection1.add(item);
            }
        }
        System.out.println(intersection1);


    }


}