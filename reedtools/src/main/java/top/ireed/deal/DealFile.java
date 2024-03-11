package top.ireed.deal;

import java.io.File;

/**
 * 功能简述:〈deal路径工具类〉
 *
 * @author reedsource
 * date 2024/3/8 20:31
 * reedsource@189.cn
 */
public class DealFile {

    public static String getUserFile() {
        File file = new File("");
        return file.getAbsolutePath();
    }


    /**
     * @return 返回一个测试数据保存专用路径
     */
    public static String getUserTestFile() {
        File file = new File("");
        return file.getAbsolutePath()+"\\src\\test\\java\\top\\ireed\\data";
    }
}