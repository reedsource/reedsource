package top.ireed.deal;

import junit.framework.Assert;
import org.junit.Test;

/**
 * 功能简述:〈deal路径测试工具类〉
 *
 * @author reedsource
 * date 2024/3/8 20:31
 * reedsource@189.cn
 */
public class DealFileTest{

    @Test
    public void DealFileTest(){
        DealLog.log(DealFile.getUserFile());
        Assert.assertTrue(true);
    }



}