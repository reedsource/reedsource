package top.ireed.service;

import top.ireed.model.OneTable;

import java.util.List;


/**
 * 功能简述:
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
public interface MybatisService {

	/**
	 * 查询
	 *
	 * @return 全部数据
	 */
	List<OneTable> all();

	/**
	 * 多条件查询数据
	 *
	 * @param oneTable 实体
	 * @return data
	 */
	List<OneTable> inAll(OneTable oneTable);
}
