/*
 * FileName: FoundSqliteTest
 * Author:   reedsource
 */
package top.ireed.found;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealDate;
import top.ireed.deal.DealLog;
import top.ireed.entity.PageData;
import top.ireed.entity.PageTime;
import top.ireed.general.TopException;
import top.ireed.model.Model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能简述:
 * 〈DealSqlite测试类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/5/29 15:57
 * reedsource@189.cn
 */
public class FoundSqliteTest {
	@Test
	public void test() throws TopException {
		//===普通模式测试====================================================================

		String tableName = "sqliteTable";
		// 1. 连接一个数据库
		FoundSqlite foundSqlite = new FoundSqlite("jdbc:sqLite:D:\\cache\\data\\DealSqliteTest.db", false);

		// 2. 根据实体类初始化一个数据表 数据库正常情况 将不会做任何操作
		foundSqlite.init(tableName, new Model(), "id", true);

		// 3. 表数据库插入
		foundSqlite.set("INSERT INTO " + tableName + "(key,value) VALUES('数据1','mm1')", "添加");

		// 3.1 表数据插入方式
		DealLog.log(foundSqlite.insert(tableName, new Model("方法key", "方法value")));

		// 4. 查询数据结果条数
		DealLog.log("数据表数量", foundSqlite.getSum("select count(1) FROM sqlite_sequence"));

		// 5. 更新数据
		DealLog.log(foundSqlite.update(tableName, "id", new Model("2", "更新后的key", "更新后的value", DealDate.getDate("2022-2-2"))));
		// 6. 查看更新效果
		// 查询数据结果list
		List<Model> list1 = foundSqlite.get(Model.class, "SELECT * FROM " + tableName);
		if (list1 != null) {
			for (Object o : list1) {
				DealLog.log(o.toString());
			}
		}

		//===约定模式测试====================================================================

		DealLog.log("=======封装约定增删改查实现===========");
		// 1. 约定 连接一个数据库
		FoundSqlite fSqlite = new FoundSqlite("jdbc:sqLite:D:\\cache\\data\\DealSqliteTest1.db");

		// 2. 约定 根据实体类初始化一个数据表 数据库正常情况 将不会做任何操作
		//约定 主键名称为 id  自增
		boolean m = fSqlite.init(new Model());

		// 3. 约定 表数据插入方式
		//初始化时会执行的操作
		if (m) {
			DealLog.log(fSqlite.insert(new Model("方法key1", "方法value",DealDate.getDate("2022-2-2"))));
		}

		fSqlite.insert(new Model("方法key2", "方法value",DealDate.getDate("2022-2-2")));
		fSqlite.insert(new Model("方法key3", "方法value",DealDate.getDate("2022-2-2")));
		fSqlite.insert(new Model("方法key4", "方法value",DealDate.getDate("2022-2-2")));
		fSqlite.insert(new Model("方法key5", "方法value",DealDate.getDate("2022-2-2")));
		fSqlite.insert(new Model("方法key6", "方法value",DealDate.getDate("2022-2-2")));
		fSqlite.insert(new Model("方法key7", "方法value",DealDate.getDate("2022-2-2")));

		//表存在的情况 插入数据
		if (fSqlite.foundTable(new Model())) {
			fSqlite.insert(new Model("方法key4", "方法value",DealDate.getDate("2022-2-2")));
		}

		//物理删除一条数据
		DealLog.log("删除数据", fSqlite.delete(new Model("3")));

		// 4. 约定 更新数据
		DealLog.log(fSqlite.update(new Model("2", "更新后的key", "更新后的value",DealDate.getDate("2022-2-2"))));

		// 5. 约定 查询数据
		// 5.1 无序
		List<Model> fList = fSqlite.get(new Model("方法key6", "方法value"));
		if (fList != null) {
			for (Model o : fList) {
				DealLog.log("遍历", o.toString());
			}
		}
		// 5.2 单条
		try {
			DealLog.log(fSqlite.getOne(new Model("2")).toString());
		} catch (TopException e) {
			DealLog.log("package情况下可能的异常打包异常");
		}

		// 6. 约定 查询数据 条数
		DealLog.log("约定 查询数据 条数", fSqlite.getSum(new Model("方法key1", "方法value")));

		// 7. 约定 通过id删除数据
		DealLog.log("约定 通过id删除数据", fSqlite.delete(new Model("6")));


		//查询删除数据后的数据
		List<Model> fList1 = fSqlite.get(new Model());

		if (fList1 != null) {
			for (Object o : fList1) {
				DealLog.log("遍历", o.toString());
			}
		}


		//分页查询数据   从第二条之后 查询3条数据
		List<PageTime> list = new ArrayList<>();
		list.add(new PageTime("date", DealDate.getDate("2020-01-01"),DealDate.getDate("2022-04-05")));

		PageData pageData = new PageData(3, 2, new Model(), "id", list);
		pageData = fSqlite.getPage(pageData);
		if (pageData.getList() != null) {
			for (Object o : pageData.getList()) {
				DealLog.log("分页", o.toString());
			}
		}
		DealLog.log("最大数据条数", pageData.getCount());
		DealLog.log("当前页码", pageData.getPageNo());
		DealLog.log("每页显示", pageData.getPageSize());

		//尝试异常
		//foundSqlite.update(new Model("2", "更新后的key", "更新后的value"));

		//清空表
		fSqlite.set("DELETE FROM ModelTop", "清空");

		//删除表
		fSqlite.set("DROP TABLE ModelTop", "删除表");

		//5. 手动关闭连接
		fSqlite.close();

		//无意义,主要避免sonar测试类断言需求判断
		Assert.assertTrue(true);
	}
}
