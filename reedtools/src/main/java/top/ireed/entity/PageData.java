/*
 * FileName: PageData
 * Author:   reedsource
 */
package top.ireed.entity;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能简述:
 * 〈通用的分页实体对象〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/10/19 15:44
 * reedsource@189.cn
 */
public class PageData<T> {

	/**
	 * 继承字段 id
	 */
	private String id;

	//约定时间组部分
	//查询多个时间时 加入多个即可 确保时间字段存在
	private List<PageTime> timeList;

	//分页部分
	/**
	 * 当前页码 默认第一页
	 */
	private int pageNo = 1;
	/**
	 * 页面每页条数 默认每页15条
	 */
	private int pageSize = 10;
	/**
	 * 总条数
	 */
	private int count;
	/**
	 * 首页索引 必然有第一页
	 */
	private int first = 1;
	/**
	 * 当前sql
	 */
	private String sql;
	/**
	 * 查询的结果数据集合
	 */
	private List<T> list;
	/**
	 * group字段名称
	 */
	private String orderName;
	/**
	 * 降序?
	 * ORDER BY " + bayName + " DESC
	 */
	private Boolean orderByDesc;
	/**
	 * 查询数据条件
	 */
	private Object object;

	public PageData() {
	}

	/**
	 * @param pageNo   当前页码
	 * @param pageSize 每页数据条数
	 * @param object   查询的数据
	 */
	public PageData(int pageNo, int pageSize, Object object) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.object = object;
	}

	/**
	 * @param pageNo   当前页码
	 * @param pageSize 每页数据条数
	 * @param object   查询的数据
	 * @param orderName   排序字段
	 */
	public PageData(int pageNo, int pageSize, Object object,String orderName,List<PageTime> timeList) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.object = object;
		this.orderName = orderName;
		this.timeList = timeList;
	}

	/**
	 * 使用缓存cookie构建分页
	 */
	public PageData(HttpServletRequest request, Object object) {
		//获取请求中的分页页码
		String pageNoStr = request.getParameter("pageNo");
		//获取请求中的每页显示数量
		String pageSizeStr = request.getParameter("pageSize");
		if (StrUtil.isNotBlank(pageNoStr)) {
			this.pageNo = Integer.valueOf(pageNoStr);
		}
		if (StrUtil.isNotBlank(pageSizeStr)) {
			this.pageSize = Integer.valueOf(pageSizeStr);
		}
		//分页查询对象
		this.object = object;
	}

	/**
	 * 当前分页查询查询第几页
	 *
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 当前分页查询的开始条数
	 *
	 * @return
	 */
	public int getLimitBegin() {
		//第一页从0开始
		return (pageNo - 1) * pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 首页索引
	 *
	 * @return 数据
	 */
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	/**
	 * 尾页索引
	 *
	 * @return 数据
	 */
	public int getLast() {
		//尾页 向上取整
		return (int) Math.ceil((double) count / pageSize);
	}

	/**
	 * 上一页索引
	 *
	 * @return 数据
	 */
	public int getPrev() {
		if (pageNo > first) {
			return pageNo - 1;
		} else {
			return first;
		}
	}

	/**
	 * 下一页索引
	 *
	 * @return 数据
	 */
	public int getNext() {
		if (pageNo < getLast()) {
			return pageNo + 1;
		} else {
			return first;
		}
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Boolean getOrderByDesc() {
		return orderByDesc;
	}

	public void setOrderByDesc(Boolean orderByDesc) {
		this.orderByDesc = orderByDesc;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PageTime> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<PageTime> timeList) {
		this.timeList = timeList;
	}
}
