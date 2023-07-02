/*
 * FileName: ListStreamTest
 * Author:   reedsource
 */
package k12集合基础.List.list创建及Lambda方法;


import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 功能简述:
 * 〈jdk1.8 对list的各种操作〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class ListStreamTest {

    /**
     * Lists.newArrayList()
     *
     * @return list集合对象
     */
    private static List<Model> getList() {
        List<Model> list = new ArrayList<>();
        Model Model = new Model();
        Model.setId(2);/*主键*/
        Model.setName("张三");/*姓名*/
        Model.setClasses(1);/*班级*/
        Model.setGrade(1);/*年级*/
        Model.setScore(80);/*成绩*/
        list.add(Model);

        Model model1 = new Model();
        model1.setId(1);
        model1.setName("李四");
        model1.setClasses(1);
        model1.setGrade(1);
        model1.setScore(60);
        list.add(model1);

        Model model2 = new Model();
        model2.setId(3);
        model2.setName("王二麻子");
        model2.setClasses(2);
        model2.setGrade(1);
        model2.setScore(90);
        list.add(model2);

        Model model3 = new Model();
        model3.setId(4);
        model3.setName("王五");
        model3.setClasses(2);
        model3.setGrade(1);
        model3.setScore(59.5);
        list.add(model3);

        Model model4 = new Model();
        model4.setId(8);
        model4.setName("小明");
        model4.setClasses(1);
        model4.setGrade(2);
        model4.setScore(79.5);
        list.add(model4);

        Model model5 = new Model();
        model5.setId(5);
        model5.setName("小红");
        model5.setClasses(2);
        model5.setGrade(2);
        model5.setScore(99);
        list.add(model5);

        Model model6 = new Model();
        model6.setId(7);
        model6.setName("小黑");
        model6.setClasses(2);
        model6.setGrade(2);
        model6.setScore(45);
        list.add(model6);

        Model model7 = new Model();
        model7.setId(6);
        model7.setName("小白");
        model7.setClasses(1);
        model7.setGrade(2);
        model7.setScore(88.8);
        list.add(model7);

        Model model8 = new Model();
        model8.setId(6);
        model8.setName("小蓝");
        model8.setClasses(1);
        model8.setGrade(2);
        model8.setScore(88.8);
        list.add(model8);
        return list;
    }

    public static void main(String[] args) {
        List<Model> list = getList();

        Model model8 = new Model();
        model8.setId(6);
        model8.setName("小白");
        model8.setClasses(1);
        model8.setGrade(2);
        model8.setScore(88.8);
        list.add(model8);

        /*判断元素是否在list中*/
        boolean result = list.contains(model8);
        DealLog.log(result);


        /*去重，去除重复对象（每个属性的值都一样的），需要注意的是要先重写对象TestStreamModel的equals和hashCode方法*/
        DealLog.log("去重前：" + list);
        List<Model> distinctList = list.stream().distinct().collect(Collectors.toList());
        DealLog.log("去重后：" + distinctList);

        /*排序，按id升续排列，如果要降续则改成：(a, b) -> b.getId() - a.getId(); a和b都是变量名（可以按自己意愿取名字），都是list中的对象的实例*/
        DealLog.log("排序前：" + list);
        List<Model> sortList = list.stream().sorted((a, b) -> a.getId() - b.getId()).collect(Collectors.toList());
        DealLog.log("排序后" + sortList);

        /*过滤，按照自己的需求来筛选list中的数据，比如我筛选出不及格的（小于60分）的人,t为实例*/
        DealLog.log("数值过滤前：" + list);
        List<Model> filterList = list.stream().filter(t -> t.getScore() < 60).collect(Collectors.toList());
        DealLog.log("过滤后" + filterList);


        /*过滤，按照自己的需求来筛选list中的数据，比如我筛选出名称等于小白的数据,t为实例*/
        DealLog.log("过滤前：" + list);
        List<Model> filterList1 = list.stream().filter(t -> "小白".equals(t.getName())).collect(Collectors.toList());
        DealLog.log("字符串筛选过滤后" + filterList1);

        /*map, 提取对象中的某一元素，例子中我取的是每个人的name，注意list中类型对应，如果取的是id或者班级，就应该是integer类型*/
        DealLog.log("提取前：" + list);
        List<String> mapList = list.stream().map(t -> t.getName()).collect(Collectors.toList());
        DealLog.log("提取后：" + mapList);

        /*统计，统计所有人分数的和, 主要我设置的分数属性是double类型的，所以用mapToDouble，如果是int类型的，则需要用mapToInt*/
        double sum = list.stream().mapToDouble(t -> t.getScore()).sum();
        int count = list.stream().mapToInt(t -> t.getId()).sum();

        /*分组， 按照字段中某个属性将list分组*/
        Map<Integer, List<Model>> map = list.stream().collect(Collectors.groupingBy(t -> t.getGrade()));
        DealLog.log("按年级分组" + map);
        /*然后再对map处理，这样就方便取出自己要的数据*/
        for (Map.Entry<Integer, List<Model>> entry : map.entrySet()) {
            DealLog.log("key:" + entry.getKey());
            DealLog.log("value:" + entry.getValue());
        }

        /*多重分组，先按年级分组，再按班级分组*/
        Map<Integer/*年级id*/, Map<Integer/*班级id*/, List<Model>>> groupMap = list.stream().collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses())));
        DealLog.log("按照年级再按班级分组：" + groupMap);
        DealLog.log("取出一年级一班的list：" + groupMap.get(1).get(1));

        /*多重分组，一般多重分组后都是为了统计，比如说统计每个年级，每个班的总分数*/
        Map<Integer/*年级id*/, Map<Integer/*班级id*/, Double>> sumMap = list.stream().collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses(), Collectors.summingDouble(t -> t.getScore()))));
        DealLog.log(sumMap);
        DealLog.log("取出一年级一班的总分：" + sumMap.get(1).get(1));

        /*stream是链式的，这些功能是可以一起使用的，例如：计算每个年级每个班的及格人数*/
        Map<Integer/*年级*/, Map<Integer/*班级*/, Long/*人数*/>> integerMap = list.stream().filter(t -> t.getScore() >= 60).collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses(), Collectors.counting())));
        DealLog.log("取出一年级一班及格人数：" + integerMap.get(1).get(1));
    }

}
