package top.ireed.deal;

import org.junit.Test;

/**
 * 功能简述:
 * 〈${DESCRIPTION}〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 18:27
 * reedsource@189.cn
 */
public class DealContentsTest {

    private static final String D_CACHE_PATH = DealFile.getUserTestCacheFile();

    @Test
    public void getDealContents() {
        DealLog.log("未\\结尾的目录路径创建", DealContents.newContents(D_CACHE_PATH + "\\未结尾的目录路径创建", false));
        DealLog.log("有\\结尾的目录路径创建", DealContents.newContents(D_CACHE_PATH + "\\有结尾的目录路径创建\\", false));
        // 以上两种都可以正常创建

        DealLog.log("文件父级目录创建", DealContents.newContents(D_CACHE_PATH + "\\文件父级目录创建\\文件父级目录创建文件.txt", true));
        DealLog.log("文件父级目录创建反例,特殊情况需要包含.的目录可用", DealContents.newContents(D_CACHE_PATH + "\\文件父级目录创建反例\\文件父级目录创建文件.txt", false));

        //自动判断文件目录属性创建父级目录
        DealLog.log("自动判断文件目录属性创建父级目录", DealContents.newContents(D_CACHE_PATH + "\\自动判断文件目录预期创建1\\预期不创建文件.txt"));
        DealLog.log("自动判断文件目录属性创建父级目录", DealContents.newContents(D_CACHE_PATH + "\\自动判断文件目录预期创建2\\预期创建只是目录"));
    }
}