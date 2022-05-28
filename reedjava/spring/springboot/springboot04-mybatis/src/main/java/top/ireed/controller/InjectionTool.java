/*
 * FileName: InjectionTool
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ireed.mapper.OneTableMapper;
import top.ireed.model.OneTable;

import java.util.List;

/**
 * 功能简述:
 * 〈将使用到mybatis的方法 提取制作工具类的引用方案〉
 * 解决工具类@Autowired注入为null
 * <p>
 * 1. 工具类中使用 @Autowired 方式注入  引用此工具类的方法 也需要使用 @Autowired 方式注入工具类
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */

@Component
public class InjectionTool {
	@Autowired
	private OneTableMapper oneTableMapper;

	/**
	 * 添加事务
	 *
	 * @return 全部数据
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<OneTable> all() {
		return oneTableMapper.all();
	}
}
