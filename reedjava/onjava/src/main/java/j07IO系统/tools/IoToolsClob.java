package main.java.j07IO系统.tools;


import top.ireed.deal.DealLog;

import java.io.BufferedReader;
import java.sql.Clob;

/**
 * 用于处理数据库中的大字段数据读取
 */
public class IoToolsClob {

    /**
     * 用于限制读取数据循环次数,避免因为异常的数据条数,导致过度占用服务器资源
     */
    private static final int LOOP_MAX_COUNT = 10000;

    /**
     * 将数据库CLOB类型数据转换为字符串
     *
     * @param clob 字符串
     * @return 字符串
     */
    public static String getClobString(Object clob) {
        StringBuilder sb = new StringBuilder();
        try (java.io.Reader is = ((Clob) clob).getCharacterStream();
             BufferedReader br = new BufferedReader(is)) {
            int i = 0;
            String s = br.readLine();
            //注意这里是按行读取的数据,原始的clob对象不应该有大量的换行符
            while (i < LOOP_MAX_COUNT && s != null) {
                i++;
                sb.append(s);
                s = br.readLine();
            }
        } catch (Exception e) {
            DealLog.log("IoToolsClob.getClobString：", e);
            return "";
        }
        return sb.toString();
    }
}