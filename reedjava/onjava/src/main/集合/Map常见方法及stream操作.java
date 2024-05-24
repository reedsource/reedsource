package main.集合;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能简述:〈〉
 *
 * @author reedsource
 * date 2024/4/16 17:10
 * reedsource@189.cn
 */
public class Map常见方法及stream操作 {

    @Test
    public void map_Test() {
        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            params.put(String.valueOf(i), String.valueOf(i));
        }

        String[] aer = new String[]{"1", "2", "3"};


        //流删除指定key数组数据
        Map<String, String> collect = params.entrySet().stream().filter((e) -> !Arrays.toString(aer).contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (java.util.Map.Entry<String, String> stringStringEntry : collect.entrySet()) {
            System.out.print(stringStringEntry.getKey());
            System.out.print("   -   ");
            System.out.println(stringStringEntry.getValue());
        }

    }

}