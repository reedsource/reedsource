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
        DealIo.toFileIo(new File("D:\\cache\\io\\io测试数据.txt"), "测试内容111");
        Assert.assertTrue(true);
    }


    public void testStringToFile() {

        String filePath = "D:\\cache\\io\\ioFile测试数据.docx";

        String fileContent = fileToString(filePath);

        String outFilePath = "D:\\cache\\io\\" + System.currentTimeMillis() + File.separator + "ioFile测试数据.docx";
        Boolean is = stringToFile(fileContent, outFilePath);

    }
}