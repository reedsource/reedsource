/*
 * FileName: DataInit
 * Author:   reedsource
 */
package top.ireed.init;

import top.ireed.deal.DealLog;
import top.ireed.found.FoundSqlite;
import top.ireed.general.TopException;
import top.ireed.model.OneTable;

import java.util.List;

/**
 * 功能简述:
 * 〈项目数据库初始化〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
public class DataInit {

	public static void init() throws TopException {
		DealLog.log("Springboot04MybatisApplication 数据初始化开始");
		// 1. 连接一个数据库
		FoundSqlite foundSqlite = new FoundSqlite("jdbc:sqLite:D:\\cache\\data\\myBatisData.db");

		//删除表
		foundSqlite.set("DELETE FROM OneTableTop","删除表");

		// 2. 初始化一个数据库
		foundSqlite.init(new OneTable());

		// 3. 表数据库插入
		foundSqlite.insert(new OneTable("张1", "11"));
		foundSqlite.insert(new OneTable("张2", "12"));
		foundSqlite.insert(new OneTable("张3", "13"));
		foundSqlite.insert(new OneTable("张4", "14"));
		foundSqlite.insert(new OneTable("张5", "15"));
		foundSqlite.insert(new OneTable("张6", "16"));
		foundSqlite.insert(new OneTable("张7", "17"));


		// 4. 查询数据结果json

		List<OneTable> list = foundSqlite.get(new OneTable());
		for (OneTable o : list) {
			DealLog.log(o.toString());
		}

		DealLog.log("Springboot04MybatisApplication 数据初始化结束");
	}
}
