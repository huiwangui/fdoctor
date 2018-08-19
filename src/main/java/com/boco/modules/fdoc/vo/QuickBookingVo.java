package com.boco.modules.fdoc.vo;
/**
 * 快速预约查询参数对象
 * @author q
 *
 */
public class QuickBookingVo {
	public Integer period; //预约时间段：1-上午，2-下午，3-夜诊
	public Long worktime; //工作日期时间戳
	public Integer hospId; //医院ID
	public Integer deptId; //科室ID
	
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Long getWorktime() {
		return worktime;
	}
	public void setWorktime(Long worktime) {
		this.worktime = worktime;
	}
	public Integer getHospId() {
		return hospId;
	}
	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
}
