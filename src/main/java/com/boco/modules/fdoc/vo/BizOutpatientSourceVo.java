package com.boco.modules.fdoc.vo;

import java.math.BigDecimal;
import java.util.Date;

public class BizOutpatientSourceVo {
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 坐诊日期
	 */
	private Date clinicDate;
	/**
	 * 机构ID
	 */
	private Integer orgId;
	/**
	 * 机构名
	 */
	private String orgName;
	/**
	 * 科室ID
	 */
	private Integer deptId;
	/**
	 * 科室名
	 */
	private String deptName;
	/**
	 * 医生ID
	 */
	private Integer staffId;
	/**
	 * 医生姓名
	 */
	private String staffName;
	/**
	 * 门诊类别名称
	 */
	private String outpatientType;
	/**
	 * 0.上午 1.下午 2.全天 3.晚上 4.休息
	 */
	private String clinicTime;
	/**
	 * 星期，值为1-7
	 */
	private String weekDay;
	/**
	 * 挂号限额，指挂号的总限额，包括现场挂号和预约
	 */
	private Integer registrationLimit;

	/**
	 * 预约限号数，指通过预约途径挂号的限额
	 */
	private Integer orderLimit;

	/**
	 * 当前号数（已挂多少个）
	 */
	private Integer currentNumber;

	/**
	 * 剩余号数
	 */
	private Integer remainderNumber;
	
	/**
	 * 查询开始时间
	 */
	private Date queryStartTime;
	
	/**
	 * 查询结束时间
	 */
	private Date queryEndDate;
	
	/**
	 * 坐诊日期字符串（yyyy-mm-dd）
	 */
	private String clinicDateStr;
	
	/**
	 * 坐诊日期字符串（mm/dd）
	 */
	private String clinicSimpleDateStr;
	
	/**
	 * 星期中文显示
	 */
	private String weekDayChs;
	
	/**
	 * 上下午中文显示
	 */
	private String clinicTimeChs;
	
	/**
	 * 当排班列表上午、下午均有排班的时候，用于上午排班的对象
	 */
	private BizOutpatientSourceVo amBizOutpatientSourceVo;
	
	/**
	 * 当排班列表上午、下午均有排班的时候，用于下午排班的对象
	 */
	private BizOutpatientSourceVo pmBizOutpatientSourceVo;
	
	/**
     * 挂号费
     */
    private BigDecimal registrationFee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getClinicDate() {
		return clinicDate;
	}

	public void setClinicDate(Date clinicDate) {
		this.clinicDate = clinicDate;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getOutpatientType() {
		return outpatientType;
	}

	public void setOutpatientType(String outpatientType) {
		this.outpatientType = outpatientType;
	}

	public String getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Integer getRegistrationLimit() {
		return registrationLimit;
	}

	public void setRegistrationLimit(Integer registrationLimit) {
		this.registrationLimit = registrationLimit;
	}

	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public Integer getRemainderNumber() {
		return remainderNumber;
	}

	public void setRemainderNumber(Integer remainderNumber) {
		this.remainderNumber = remainderNumber;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(Date queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public Date getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public String getClinicDateStr() {
		return clinicDateStr;
	}

	public void setClinicDateStr(String clinicDateStr) {
		this.clinicDateStr = clinicDateStr;
	}

	public String getWeekDayChs() {
		return weekDayChs;
	}

	public void setWeekDayChs(String weekDayChs) {
		this.weekDayChs = weekDayChs;
	}

	public String getClinicTimeChs() {
		return clinicTimeChs;
	}

	public void setClinicTimeChs(String clinicTimeChs) {
		this.clinicTimeChs = clinicTimeChs;
	}

	public String getClinicSimpleDateStr() {
		return clinicSimpleDateStr;
	}

	public void setClinicSimpleDateStr(String clinicSimpleDateStr) {
		this.clinicSimpleDateStr = clinicSimpleDateStr;
	}

	public BizOutpatientSourceVo getAmBizOutpatientSourceVo() {
		return amBizOutpatientSourceVo;
	}

	public void setAmBizOutpatientSourceVo(
			BizOutpatientSourceVo amBizOutpatientSourceVo) {
		this.amBizOutpatientSourceVo = amBizOutpatientSourceVo;
	}

	public BizOutpatientSourceVo getPmBizOutpatientSourceVo() {
		return pmBizOutpatientSourceVo;
	}

	public void setPmBizOutpatientSourceVo(
			BizOutpatientSourceVo pmBizOutpatientSourceVo) {
		this.pmBizOutpatientSourceVo = pmBizOutpatientSourceVo;
	}

	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}
}
