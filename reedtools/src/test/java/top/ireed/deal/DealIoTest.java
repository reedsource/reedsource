package top.ireed.deal;

import junit.framework.TestCase;
import org.junit.Assert;
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

    public void testToFile() throws TopException {
        DealIo.toFileIo(new File("D:\\clouds\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\data\\io测试数据.txt"), "测试内容111");
        Assert.assertTrue(true);
    }


    public void testStringToFile() throws TopException {

        String filePath = "D:\\clouds\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\data\\io测试数据.txt";

        String fileContent = fileToString(filePath);
        DealIo.toFileIo(new File("D:\\clouds\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\data\\io加密后测试数据.txt"), fileContent);

        String outFilePath = "D:\\clouds\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\data\\" + "io加密后解密测试数据.txt";
        Boolean is = stringToFile(fileContent, outFilePath);

    }
}