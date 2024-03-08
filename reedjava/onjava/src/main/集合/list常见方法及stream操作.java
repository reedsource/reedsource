package main.集合;

import org.junit.Test;
import top.ireed.deal.DealDate;
import top.ireed.deal.DealLog;
import top.ireed.entity.OnJava;
import top.ireed.general.TopException;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 简述:list常见方法及stream操作
 *
 * @author reedsource
 * date 2024/2/4 10:04
 * reedsource@189.cn
 */
public class list常见方法及stream操作 {

    Date date = new Date();

    List<OnJava> list = new ArrayList<>() {{
        try {
            add(new OnJava(1L, "onJava1", "一", 10, false, DealDate.getDate("2024-01-01")));
            add(new OnJava(3L, "onJava3", "三", 30, false, DealDate.getDate("2024-01-03")));
            add(new OnJava(2L, "onJava2", "二", 20, false, DealDate.getDate("2024-01-02")));
            add(new OnJava(5L, "onJava5", "五", 50, false, DealDate.getDate("2024-01-05")));
            add(new OnJava(4L, "onJava4", "六", 50, false, DealDate.getDate("2024-01-04")));
            add(new OnJava(6L, "onJava6", "六", 60, true, DealDate.getDate("2024-01-06")));
            add(new OnJava(7L, "onJava7", "七", 70, true, DealDate.getDate("2024-01-07")));
        } catch (TopException e) {
            throw new RuntimeException(e);
        }
    }};


    @Test
    public void list创建及常用_Test() {
        List<OnJava> list1 = new ArrayList<>();
        list1.add(new OnJava(10L, "onJava10", "十", 100, false, date));
        list1.add(new OnJava(11L, "onJava11", "十一", 100, false, date));
        list1.add(new OnJava(12L, "onJava12", "十二", 100, false, date));

        //list合并
        list.addAll(list1);

        //清空 清空不影响已经做出的list合并
        list1.clear();

        OnJava onJava = new OnJava(10L, "onJava10", "十", 100, false, date);
        //contains 判断元素是否包含
        //底层是依赖equals方法进行判断是否存在的，如果存的是自定对象，
        //这一定要重写equals（在自定义的javaBean中重写equals就行
        boolean is = true;
        if (list.contains(onJava)) {
            is = false;
            //删除成员
            list.remove(onJava);
        }

        //判断集合是否为空 list.isEmpty()
        //集合的长度
        if (list.size() > 5) {
            DealLog.log(is);
        }

        for (OnJava on : list) {
            DealLog.log(on);
        }

    }

    @Test
    public void list遍历_Test() {
        //普通增强遍历
        for (int i = 0; i < list.size(); i++) {
            if (i > 3) {
                DealLog.log(list.get(i));
            }
        }

        //普通for增强遍历
        for (OnJava onJava : list) {
            DealLog.log(onJava);
        }

        //迭代器遍历 遍历过程中需要删除某个元素
        Iterator<OnJava> ite = list.iterator();
        //判断下一个元素之后有值
        while (ite.hasNext()) {
            OnJava onJava = ite.next();
            if (onJava.getId() == 2) {
                ite.remove();
            }
            DealLog.log(ite.next());
        }

        //Lambda表达式
        list.forEach(onJava -> DealLog.log(onJava));

    }

    @Test
    public void list转换_Test() {
        //list变为数组相关的内容进行遍历
        OnJava[] strArray = new OnJava[list.size()];
        list.toArray(strArray);
        for (OnJava s : strArray) {
            DealLog.log(s);
        }
    }

    @Test
    public void listStream_Test() throws TopException {
        List<OnJava> onJavas;

        onJavas = list.stream().filter(t -> t.getNum() <= 30).collect(Collectors.toList());
        DealLog.logListGo("筛选  数值小于等于30", onJavas);

        onJavas = list.stream().filter(t -> t.getNum() <= 30 && t.getNum() > 10).collect(Collectors.toList());
        DealLog.logListGo("筛选  数值小于等于30 大于10", onJavas);

        onJavas = list.stream().filter(t -> "onJava7".equals(t.getName())).collect(Collectors.toList());
        DealLog.logListGo("筛选  字符串匹配", onJavas);

        list.add(new OnJava(7L, "onJava7", "七", 70, false, DealDate.getDate("2024-01-07")));
        DealLog.logListGo("去重  将list中的重复数据去除前", list);
        onJavas = list.stream().distinct().collect(Collectors.toList());
        DealLog.logListGo("去重  将list中的重复数据去除后", onJavas);

        onJavas = list.stream().sorted(Comparator.comparingLong(OnJava::getId)).collect(Collectors.toList());
        DealLog.logListGo("排序  对数值id进行升序排序", onJavas);

        onJavas = list.stream().sorted((a, b) -> b.getNum() - a.getNum()).collect(Collectors.toList());
        DealLog.logListGo("排序  对数值id进行降序排序", onJavas);

        onJavas = list.stream().sorted(Comparator.comparing(OnJava::getName)).collect(Collectors.toList());
        DealLog.logListGo("排序  对英文字符进行升序排序", onJavas);

        onJavas = list.stream().sorted(Comparator.comparing(OnJava::getDate)).collect(Collectors.toList());
        DealLog.logListGo("排序  对时间进行升序排序", onJavas);

        Collator collator = Collator.getInstance(); // 获取默认的Collator对象
        list.sort((s1, s2) -> collator.compare(s2.getName(), s1.getName())); // 根据Collator对象比较字符串并排序
        DealLog.logListGo("排序  对英文字符进行降序排序", list);

        List<String> sList = list.stream().map(t -> t.getName()).collect(Collectors.toList());
        DealLog.logListGo("提取  list字段list", sList);

        int sum = list.stream().mapToInt(t -> t.getNum()).sum();
        DealLog.log("统计list int类型数据和", sum);

        sum = list.stream().filter(t -> t.getNum() <= 30).toList().stream().mapToInt(t -> t.getNum()).sum();
        DealLog.log("统计list 筛选 数值小于等于30 int类型数据和", sum);

        Map<Integer, List<OnJava>> intMap = list.stream().collect(Collectors.groupingBy(t -> t.getNum()));
        DealLog.logMapGo("分组 list数值分组", intMap);

        Map<String, List<OnJava>> stringMap = list.stream().collect(Collectors.groupingBy(t -> t.getcName()));
        DealLog.logMapGo("分组 list字符串分组", stringMap);

        Map<Boolean, List<OnJava>> boMap = list.stream().collect(Collectors.groupingBy(t -> t.isState()));
        DealLog.logMapGo("分组 list Boolean分组", boMap);

        Map<Boolean, Map<Integer, List<OnJava>>> groupMap = list.stream().collect(Collectors.groupingBy(t -> t.isState(), Collectors.groupingBy(t -> t.getNum())));
        DealLog.logMapGo("多重分组 list Boolean 数值 分组", groupMap);


        Map<Boolean, Integer> groupSumMap = list.stream().collect(Collectors.groupingBy(t -> t.isState(), Collectors.summingInt(t -> t.getNum())));
        DealLog.logMapGo("多重分组 list Boolean 分组 数值求和", groupSumMap);

        Map<Boolean, Map<Integer, Long>> groupSuMap = list.stream().collect(Collectors.groupingBy(t -> t.isState(), Collectors.groupingBy(t -> t.getNum(), Collectors.counting())));
        DealLog.logMapGo("多重分组 list Boolean 分组 分值数量分组", groupSuMap);
    }

}
