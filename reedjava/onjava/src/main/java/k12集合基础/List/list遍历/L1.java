/*
 * FileName: L1
 * Author:   reedsource
 */
package k12集合基础.List.list遍历;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class L1 {

    @Test
    public void l1() {
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");
        //第一种遍历方法使用 For-Each 遍历 List

        for (String str : list) {
            DealLog.log(str);
        }

        //第二种遍历，把链表变为数组相关的内容进行遍历
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        for (int i = 0; i < strArray.length; i++) {
            DealLog.log(strArray[i]);
        }

        //第三种遍历 使用迭代器进行相关遍历

        Iterator<String> ite = list.iterator();
        //判断下一个元素之后有值
        while (ite.hasNext()) {
            DealLog.log(ite.next());
        }

    }
}
