package main.时间;

import org.junit.Test;

import java.util.Date;

public class java时间相关 {

    @Test
    public void 时间_Test() {
        //当前时间
        Date date1 = new Date(120, 10, 1);  // 创建日期对象date1，表示2020年11月1日
        int result = new Date().compareTo(date1);  // 比较date1和date2的先后顺序
        if (result < 0) {
            System.out.println("date1在date2之前");
        } else if (result > 0) {
            System.out.println("date1在date2之后");
        } else {
            System.out.println("date1和date2相等");
        }
    }
}