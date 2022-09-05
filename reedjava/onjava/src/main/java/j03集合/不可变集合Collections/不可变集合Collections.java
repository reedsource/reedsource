/*
 * Author:   reedsource
 */
package j03集合.不可变集合Collections;

import top.ireed.deal.DealLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/9/5 10:50
 * reedsource@189.cn
 */
public class 不可变集合Collections {

	/**
	 * 数据库字符串类型
	 */
	public static final List<String> COLUMN_TYPE_STR;

	//创建不可变的list
	static {
		List<String> list = new ArrayList<>(4);
		list.add("char");
		list.add("varchar");
		list.add("nvarchar");
		list.add("varchar2");

		COLUMN_TYPE_STR = Collections.unmodifiableList(list);
	}

	// 以上为原始写法

	public static final List<String> COLUMN_TYPE_STR1;

	//创建不可变的list
	static {
		COLUMN_TYPE_STR1 = List.of("char", "varchar", "nvarchar", "varchar2");
	}


	public static void main(String[] args) {

		DealLog.log(COLUMN_TYPE_STR);
		DealLog.log(COLUMN_TYPE_STR1);

	}


}
