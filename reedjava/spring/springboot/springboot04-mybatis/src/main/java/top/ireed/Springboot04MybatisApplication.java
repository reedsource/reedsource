package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;
import top.ireed.init.DataInit;

/**
 * 开启事务支持
 */
@EnableTransactionManagement
@SpringBootApplication
public class Springboot04MybatisApplication {

	public static void main(String[] args) throws TopException {
		DealLog.log("本项目使用多环境配置,第一步请勾选环境 请勾选win_linux maven中package 检查打包后的application.yml是否是编译后的正确路径");
		DealLog.log("本项目使用实体数据库,第二步 请init下 初始化数据库");
		DealLog.log("启动项目后 springWeb测试请求地址 http://localhost/all");
		DealLog.log("启动项目后 mapper工具类注入测试 请求地址 http://localhost/zhu");
		DealLog.log("启动项目后 mybatis多条件动态拼接查询测试测试 请求地址 http://localhost/in");
		//数据库初始化
		DataInit.init();
		SpringApplication.run(Springboot04MybatisApplication.class, args);
	}
}
