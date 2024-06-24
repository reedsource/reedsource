package top.ireed.found.words;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 分词工具类 游标简单实现
 * 游标实现分词
 */
public class FoundDicts02 {

    Set<String> dict = new HashSet<>();

    /**
     * 构造函数
     */
    public FoundDicts02(File file) {
        initDict(file);
    }

    private static Map<String, List<Set<String>>> getStringListMap() {
        Map<String, List<Set<String>>> map = new HashMap<>();
        {
            List<Set<String>> list = new ArrayList<>();
            //前字数组
            Set<String> lift = new HashSet<>() {{
                add("");
            }};
            //后字数组
            Set<String> right = new HashSet<>() {{
                add("成");
            }};
            //前后-前数组
            Set<String> liftOr = new HashSet<>() {{
                add("");
            }};
            //前后-后数组
            Set<String> rightOr = new HashSet<>() {{
                add("");
            }};
            list.add(lift);
            list.add(right);
            list.add(liftOr);
            list.add(rightOr);
            map.put("切分", list);
        }
        {
            List<Set<String>> list = new ArrayList<>();
            //前字数组
            Set<String> lift = new HashSet<>() {{
                add("");
            }};
            //后字数组
            Set<String> right = new HashSet<>() {{
                add("意义");
            }};
            //前后-前数组
            Set<String> liftOr = new HashSet<>() {{
                add("");
            }};
            //前后-后数组
            Set<String> rightOr = new HashSet<>() {{
                add("");
            }};
            list.add(lift);
            list.add(right);
            list.add(liftOr);
            list.add(rightOr);
            map.put("有", list);
        }
        {
            List<Set<String>> list = new ArrayList<>();
            //前字数组
            Set<String> lift = new HashSet<>() {{
                add("");
            }};
            //后字数组
            Set<String> right = new HashSet<>() {{
                add("卖");
            }};
            //前后-前数组
            Set<String> liftOr = new HashSet<>() {{
                add("");
            }};
            //前后-后数组
            Set<String> rightOr = new HashSet<>() {{
                add("");
            }};
            list.add(lift);
            list.add(right);
            list.add(liftOr);
            list.add(rightOr);
            map.put("羽毛球拍", list);
        }

        return map;
    }

    /**
     * 正反匹配组合
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String goTo(String str, int dictMaxLen) {
        String goMsg = go(str, dictMaxLen);
        String toMsg = to(str, dictMaxLen);
        if (goMsg.equals(toMsg)) {
            return "双向匹配相同，结果为：" + goMsg;
        } else {

            //词清单
            Map<String, List<Set<String>>> map = getStringListMap();

            //最终结果
            StringBuilder result = new StringBuilder();

            //正向分词集合
            List<String> goMsgList = Arrays.asList(goMsg.trim().split(" "));
            //反向分词集合
            List<String> toMsgList = Arrays.asList(toMsg.trim().split(" "));

            System.out.println("正向共 " + goMsgList.size());
            System.out.println("反向共 " + toMsgList.size());

            //正向总长度
            int goSize = goMsgList.size();
            //反向总长度
            int toSize = toMsgList.size();

            //分词位置游标
            int is = 0;

            //正确当前词
            String dict;
            //错误当前词
            String word;
            //下一个错误当前词
            String leftWord;
            //前后合并错误词
            String allWord;
            //游标小于正向分词集合长度  游标小于反向分词集合长度
            while (is < goSize && is < toSize) {

                //如果游标正反相等
                if (goMsgList.get(is).equals(toMsgList.get(is))) {
                    //写入最终分词结果
                    result.append(goMsgList.get(is)).append(" ");
                    is++;
                } else {
                    //正向当前词
                    String leftDict = goMsgList.get(is);

                    //正向匹配积分
                    int leftSum = 0;

                    {
                        //左词清单
                        List<Set<String>> list = map.get(leftDict);
                        if (list != null && list.size() == 4) {
                            //前字数组
                            Set<String> lift = list.get(0);
                            if (!lift.isEmpty()) {
                                //当前词位置不是, 有上一个词的情况
                                if (is > 0) {
                                    //查询前词组是否存在
                                    if (lift.contains(goMsgList.get(is - 1))) {
                                        leftSum++;
                                    }
                                }
                            }

                            //后字数组
                            Set<String> right = list.get(1);
                            if (!right.isEmpty()) {
                                //当前词位置不是, 有下一个词的情况
                                //查询后词组是否存在
                                if (right.contains(goMsgList.get(is + 1))) {
                                    leftSum++;
                                }
                            }
                            //前后-前数组
                            Set<String> liftOr = list.get(2);
                            //前后-后数组
                            Set<String> rightOr = list.get(3);
                            if (!liftOr.isEmpty() && !rightOr.isEmpty()) {
                                //当前词位置不是, 有上一个词的情况
                                if (is > 0) {
                                    //查询前词组是否存在
                                    if (liftOr.contains(goMsgList.get(is + 1)) && rightOr.contains(goMsgList.get(is + 1))) {
                                        leftSum++;
                                    }
                                }
                            }
                        }
                    }

                    //反向当前词
                    String rightDict = toMsgList.get(is);
                    //正向匹配积分
                    int rightSum = 0;

                    {
                        //左词清单
                        List<Set<String>> list = map.get(rightDict);
                        if (list != null && list.size() == 4) {
                            //前字数组
                            Set<String> lift = list.get(0);
                            if (!lift.isEmpty()) {
                                //当前词位置不是, 有上一个词的情况
                                if (is > 0) {
                                    //查询前词组是否存在
                                    if (lift.contains(toMsgList.get(is - 1))) {
                                        rightSum++;
                                    }
                                }
                            }

                            //后字数组
                            Set<String> right = list.get(1);
                            if (!right.isEmpty()) {
                                //当前词位置不是, 有下一个词的情况
                                //查询后词组是否存在
                                if (right.contains(toMsgList.get(is + 1))) {
                                    rightSum++;
                                }
                            }
                            //前后-前数组
                            Set<String> liftOr = list.get(2);
                            //前后-后数组
                            Set<String> rightOr = list.get(3);
                            if (!liftOr.isEmpty() && !rightOr.isEmpty()) {
                                //当前词位置不是, 有上一个词的情况
                                if (is > 0) {
                                    //查询前词组是否存在
                                    if (liftOr.contains(toMsgList.get(is + 1)) && rightOr.contains(toMsgList.get(is + 1))) {
                                        rightSum++;
                                    }
                                }
                            }
                        }
                    }
                    //正向大于反向
                    //反向包含等于的情况, 即结果偏向
                    if (leftSum > rightSum) {
                        //正向当前词
                        dict = goMsgList.get(is);
                        //更新反向分词结果
                        //反向当前词
                        word = toMsgList.get(is);
                        //获取反向下一个词
                        leftWord = toMsgList.get(is + 1);
                        //合并词语
                        allWord = word + leftWord;

                        //更新反向当前词
                        toMsgList.set(is, dict);
                        //更新反向下一词
                        toMsgList.set(is + 1, allWord.substring(dict.length()));
                    } else {
                        //反向当前词
                        dict = toMsgList.get(is);

                        //更新正向分词结果
                        word = goMsgList.get(is);
                        //获取反向下一个词
                        leftWord = goMsgList.get(is + 1);
                        //合并词语
                        allWord = word + leftWord;

                        //更新反向当前词
                        goMsgList.set(is, dict);
                        goMsgList.set(is + 1, allWord.substring(dict.length()));
                    }
                }
            }

            return result.toString();
        }
    }

    /**
     * 初始化词典
     */
    public void initDict(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                dict.add(line.trim());
            }
            br.close();
        } catch (IOException e) {
            System.out.println("初始化词典异常");
        }
    }

    /**
     * 正向最大匹配算法
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String go(String str, int dictMaxLen) {

        StringBuilder goResult = new StringBuilder();

        //匹配开始位置
        int left = 0;

        //尝试分词的词长度
        int dictLen;

        //起始位置小于字符串总长度 继续分词
        while (left < str.length()) {
            //尝试分词长度 初始化
            dictLen = dictMaxLen;

            //分词 词长度 > 0
            while (dictLen > 0) {
                //分词结果
                String curstr;
                //剩余需要分词字符串长度
                int beLeft = str.length() - left;
                //剩余需要分词字符串长度 <= 尝试分词长度   使用 剩余需要分词字符串长度 分词
                if (beLeft <= dictLen) {
                    curstr = str.substring(left, left + beLeft);
                } else {
                    curstr = str.substring(left, left + dictLen);
                }

                //字典中能查到分词
                if (dict.contains(curstr) || dictLen == 1) {
                    goResult.append(curstr).append(" ");
                    left = left + dictLen;
                    break;
                }
                //未匹配到,尝试降低词长度,再次匹配
                dictLen--;
            }
        }

        return goResult.toString();
    }

    /**
     * 反向最大匹配算法
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String to(String str, int dictMaxLen) {
        StringBuilder toResult = new StringBuilder();

        //匹配开始位置
        int right = str.length() - 1;

        //尝试分词的词长度
        int dictLen;

        //反向匹配结束位置大于-1 继续分词
        while (right > -1) {
            //尝试分词长度 初始化
            dictLen = dictMaxLen;

            //分词 词长度 > 0
            while (dictLen > 0) {

                String curstr;
                //根据词长度截取词
                if (right - dictLen + 1 >= 0) {
                    curstr = str.substring(right - dictLen + 1, right + 1);
                } else {
                    curstr = str.substring(0, right + 1);//到达句首
                }

                if (dict.contains(curstr) || dictLen == 1) {
                    toResult.insert(0, curstr + " ");
                    right = right - dictLen;
                    break;
                }

                //未匹配到,尝试降低词长度,再次匹配
                dictLen--;
            }
        }
        return toResult.toString();
    }
}