package top.ireed.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ireed.model.OneTable;

import java.util.List;

/**
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Mapper
public interface OneTableMapper {
	/**
	 * 删除
	 *
	 * @param id 主键id
	 * @return 删除条数
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入
	 *
	 * @param record 插入数据
	 * @return 插入条数
	 */
	int insert(OneTable record);

	/**
	 * 插入
	 *
	 * @param record 安全插入数据
	 * @return 插入条数
	 */
	int insertSelective(OneTable record);

	/**
	 * 查询
	 *
	 * @param id 主键id
	 * @return 通过主键查到的数据
	 */
	OneTable selectByPrimaryKey(String id);

	/**
	 * 非空数据更新
	 *
	 * @param record 数据类
	 * @return 更新的条数
	 */
	int updateByPrimaryKeySelective(OneTable record);

	/**
	 * 标准数据更新
	 *
	 * @param record 数据类
	 * @return 更新的条数
	 */
	int updateByPrimaryKey(OneTable record);

	/**
	 * 查询
	 *
	 * @return 全部数据
	 */
	List<OneTable> all();


	/**
	 * 查询
	 *
	 * @param oneTable 实体
	 * @return 全部数据
	 */
	List<OneTable> inAll(OneTable oneTable);
}