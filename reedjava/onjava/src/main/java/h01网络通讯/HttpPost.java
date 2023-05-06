package main.java.h01网络通讯;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import top.ireed.deal.DealLog;


public class HttpPost {
    /**
     * @param informURL 请求url
     * @param msg       请求信息
     * @param charset   字符集 一般UTF-8
     * @return 请求返回数据
     */
    protected String execute(String informURL, String msg, String charset) {

        try {
            // 取参数
            int timeout = 8000;

            DealLog.log("通用http请求 请求数据 informURL:" + informURL + ",msg: " + msg + ",charset: " + charset);
            // 准备请求参数
            PostMethod postMethod = new PostMethod(informURL);
            HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
            HttpConnectionManagerParams httpConnectionManagerParams = httpConnectionManager.getParams();
            //设置链接超时
            httpConnectionManagerParams.setConnectionTimeout(timeout);
            //读取超时
            httpConnectionManagerParams.setSoTimeout(timeout);
            //并发用户数量 默认2,高并发下会导致资源不足超时
            httpConnectionManagerParams.setDefaultMaxConnectionsPerHost(32);
            //最大连接数
            httpConnectionManagerParams.setMaxTotalConnections(256);
            HttpClient httpClient = new HttpClient(httpConnectionManager);
            postMethod.addRequestHeader("Content-Type", "application/json");
            postMethod.addRequestHeader("X-Request-App", "F-C");
            postMethod.addRequestHeader("Connection", "Keep-Alive");
            postMethod.addRequestHeader("Cache-Control", "no-cache");
            postMethod.addRequestHeader("Accept", "text/plain");
            postMethod.setRequestEntity(new StringRequestEntity(msg, "text/xml", charset));
            // 发送请求
            httpClient.executeMethod(postMethod);
            String receiveMsg = postMethod.getResponseBodyAsString();
            DealLog.log("通用http请求 返回数据 receiveMsg:" + receiveMsg);
            //关闭
            postMethod.releaseConnection();
            return receiveMsg;
        } catch (Exception e) {
            DealLog.log("通用http请求异常:" + e.getMessage());
        }
        return "";

    }
}