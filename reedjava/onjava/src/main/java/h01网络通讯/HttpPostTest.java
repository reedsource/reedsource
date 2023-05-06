package main.java.h01网络通讯;

import junit.framework.TestCase;
import top.ireed.deal.DealLog;

public class HttpPostTest extends TestCase {

    public void testExecute() {
         HttpPost httpPost = new HttpPost();
         DealLog.log(httpPost.execute("www.baidu.com","测试数据","UTF-8"));
    }
}