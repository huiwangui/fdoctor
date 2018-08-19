package com.boco.common.utils;

import java.util.Date;

public class WeeksInMonth {
	/**
	 * 一周起始日期
	 */
	private Date beginDate;
	/**
	 * 一周终止日期
	 */
	private Date endDate;
	/**
	 * 月份编号
	 */
	private Integer monthNum;
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
	public Integer getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}
	
}
