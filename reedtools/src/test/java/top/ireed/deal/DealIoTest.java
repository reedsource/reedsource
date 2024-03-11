package top.ireed.deal;

import junit.framework.TestCase;
import top.ireed.general.TopException;

import java.io.File;

import static top.ireed.deal.DealIo.fileToString;
import static top.ireed.deal.DealIo.stringToFile;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * date 2022/4/26 23:30
 * reedsource@189.cn
 */
public class DealIoTest extends TestCase {

    public void testStringToFile() throws TopException {
        String filePath = DealFile.getUserTestCacheFile() + "\\io测试数据.txt";

        //写入一个数据到测试文件
        DealIo.toFileIo(new File(filePath), "测试内容111");

        //读取测试文件写入到加密文件
        String fileContent = fileToString(filePath);
        DealIo.toFileIo(new File(DealFile.getUserTestCacheFile() + "\\io加密后测试数据.txt"), fileContent);

        //将加密文件解密,写入新文件
        String outFilePath = DealFile.getUserTestCacheFile() + "\\io加密后解密测试数据.txt";
        Boolean is = stringToFile(fileContent, outFilePath);

    }
}