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

    /**
     * @return 当前项目的路径
     * <p>
     * D:\clouds\codes\reedsource\reedtools
     */
    public static String getUserFile() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    /**
     * @return 返回一个测试临时数据专用目录
     * <p>
     * D:\clouds\codes\reedsource\reedtools\target\data\cache
     */
    public static String getUserTestCacheFile() {
        return getUserFile() + "\\target\\data\\cache";
    }

    /**
     * @return 返回一个测试数据目录, 目录中的数据将提交到git
     * D:\clouds\codes\reedsource\reedtools\src\test\java\top\ireed\data
     */
    public static String getUserTestDataFile() {
        return DealFile.getUserFile() + "\\src\\test\\java\\top\\ireed\\data";
    }

}