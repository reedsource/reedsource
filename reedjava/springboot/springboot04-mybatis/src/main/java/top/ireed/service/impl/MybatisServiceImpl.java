/*
 * FileName: MybatisServiceImpl
 * Author:   reedsource
 */
package top.ireed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ireed.mapper.OneTableMapper;
import top.ireed.model.OneTable;
import top.ireed.service.MybatisService;

import java.util.List;

/**
 * 功能简述:
 * 〈mybatis实现类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Service("MybatisService")
public class MybatisServiceImpl implements MybatisService {

	@Autowired
	private OneTableMapper oneTableMapper;

	/**
	 * 添加事务
	 *
	 * @return 全部数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<OneTable> all() {
		return oneTableMapper.all();
	}

	/**
	 * 多条件查询数据
	 * 1. 实现in动态拼接
	 *
	 * @param oneTable 查询实体
	 * @return date
	 */
	@Override
	public List<OneTable> inAll(OneTable oneTable) {
		return oneTableMapper.inAll(oneTable);
	}
}
