/*
 * FileName: PageTime
 * Author:   reedsource
 */
package top.ireed.entity;

import java.util.Date;

/**
 * 功能简述:
 * 〈时间类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/12/11 13:03
 * reedsource@189.cn
 */
public class PageTime {

	/**
	 * 查询时间 实体字段名称
	 */
	private String dateName;

	/**
	 * 开始时间
	 */
	private Date beginDate;

	/**
	 * 结束时间
	 */
	private Date endDate;

	public PageTime(String dateName, Date beginDate, Date endDate) {
		this.dateName = dateName;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
