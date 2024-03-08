package org.top.reed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;


@SpringBootApplication
public class Springboot15ElasticsearchApplication {

    public static void main(String[] args) throws TopException {
        SpringApplication.run(Springboot15ElasticsearchApplication.class, args);
        DealLog.log("http://localhost/es/createUserIndex?index=e1");

    }
}
