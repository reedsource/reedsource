package top.ireed.deal;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 功能简述:〈Id工具类〉
 *
 * @author reedsource
 * date 2024/4/1 0:08
 * reedsource@189.cn
 */
public class IdUtil {
    /**
     * 返回 年月日8时分秒6毫秒3+计数位 的18或19位的 全局id
     * <p>
     * Long最大长度 9223 37203 68547 75807 19位
     * 年月日8时分秒6毫秒3 共17位
     * <p>
     * 前17位为时间戳 后2位为毫秒随机数
     *
     * @return 年月日8时分秒6毫秒3+随机数 19位的 id
     */
    public static long getId() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        Random r = new Random(Long.parseLong(date));
        int be = r.nextInt(2);
        return Long.parseLong(date + be);
    }


    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            long id = getId();
            System.out.println(id);
            System.out.println(Long.toBinaryString(id));
        }

        System.out.println(System.currentTimeMillis() - a);
    }

}