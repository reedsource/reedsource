
package top.ireed.netty05httpupload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.io.File;

public class TestUp {
    public static void main(String[] args) {
        // 文件路径，修改为 D 盘根目录下的文件路径
        String filePath = "D:/hutool_email-master.zip"; // 或者 "D:\\hutool_email-master.zip"
        // 上传URL
        String url = "http://10.178.216.11:8080/post_multipart";

        // 创建文件对象
        File file = FileUtil.file(filePath);

        // 检查文件是否存在
        if (!file.exists()) {
            System.out.println("文件不存在，请检查路径：" + filePath);
            return;
        }

        // 创建POST请求
        HttpRequest request = HttpUtil.createPost(url);

        // 添加文件到请求中
        request.form("file", file);

        // 发送请求并获取响应
        HttpResponse response = request.execute();

        // 打印响应状态码和响应体
        System.out.println("Response Code: " + response.getStatus());
        System.out.println("Response Body: " + response.body());
    }
}