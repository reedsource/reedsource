package org.top.reed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;


@SpringBootApplication
public class Springboot15ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot15ElasticsearchApplication.class, args);
        DealLog.log("创建索引 http://localhost/es/createIndex");
        DealLog.log("删除索引 http://localhost/es/deleteIndex");
        DealLog.log("新增文档 http://localhost/es/addDocument");
        DealLog.log("批量新增文档 http://localhost/es/addDocumentList");
        DealLog.log("查询列表 http://localhost/es/getUserList");
        DealLog.log("更新文档 http://localhost/es/updateDocument?id=10005");
        DealLog.log("获取文档 http://localhost/es/getUserDocument?id=10005");
        DealLog.log("删除文档 http://localhost/es/deleteDocument?id=10004");
        DealLog.log("查询列表 http://localhost/es/getUserList");
        DealLog.log("城市聚合数据汇总 http://localhost/es/aggregationsSearchUser");
        DealLog.log("根据姓名搜索用户 http://localhost/es/searchUserByCity?city=北京");

    }
}
