/*
 * FileName: DealLog
 * Author:   reedsource
 */
package top.ireed.deal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ireed.general.TopException;

import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈日志打印工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/14 9:06
 * reedsource@189.cn
 */
public class DealLog {
    private DealLog() {
    }

    /**
     * 替代打印到控制台的日志,和打印到控制台效果一致
     */
    private static final Logger logger = LogManager.getLogger("SYSTEM_OUT");

    /**
     * 打印
     * <p>
     * 无元素 换行
     * 单元素 直接打印
     * 多元素 空格间隔组合打印
     *
     * @param o object对象  组
     */
    public static void log(Object... o) {
        logger.info(DealString.objectToString(o));
    }

    /**
     * 指向
     * a --> b
     *
     * @param m object对象
     * @param n object对象
     */
    public static void logTo(Object m, Object n) {
        logger.info(m.toString() + " --> " + n.toString());
    }

    /**
     * 指向 多个
     * a
     * --> b
     * --> c
     * --> d
     *
     * @param m 字符串
     * @param o object对象  组
     */
    public static void logToAll(Object m, Object... o) {
        StringBuilder a = new StringBuilder();
        a.append(m.toString());
        for (Object o1 : o) {
            a.append("\r\n  --> ").append(o1.toString());
        }
        logger.info(a.toString());
    }

    /**
     * 抛出异常字符串
     *
     * @param o 异常信息组 打印结果多个异常将空格划分
     */
    public static void logException(Object... o) {
        try {
            throw new TopException(o);
        } catch (TopException e) {
            logger.info(e);
        }
    }

    /**
     * list打印后换行
     *
     * @param list list对象  组
     */
    public static void logListGo(String msg, List<?> list) {
        logger.info(msg);
        logList(list);
        logger.info("");
    }

    /**
     * list打印
     *
     * @param list list对象  组
     */
    public static void logList(List<?> list) {
        for (Object o : list) {
            log(o);
        }
    }

    /**
     * map打印后换行
     *
     * @param map map对象  组
     */
    public static void logMapGo(String msg, Map<?, ?> map) {
        logger.info(msg);
        logMap(map);
        logger.info("");
    }

    /**
     * map打印
     *
     * @param map map对象  组
     */
    public static void logMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            log(entry.getKey(), entry.getValue());
        }
    }
}
