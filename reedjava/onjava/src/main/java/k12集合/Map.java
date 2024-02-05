package main.java.k12集合;

import main.java.j01枚举.颜色枚举;
import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.HashMap;

public class Map {

    @Test
    public void map遍历() {
        java.util.Map<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        for (java.util.Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey());
            System.out.println(stringStringEntry.getValue());
        }


    }
}
